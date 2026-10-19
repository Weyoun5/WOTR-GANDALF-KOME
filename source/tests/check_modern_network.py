from pathlib import Path
import subprocess,shutil,os,socket,time,sys,threading
sys.stdout.reconfigure(errors='backslashreplace')
work=Path(os.environ['WOTR_TEST_WORK'])
sync=work/('network-'+str(int(time.time())))
sync.mkdir()
def immutable_copy(src,dst):
    if Path(src).name in ('wotr_preferences','WOTR-GandalfV8-KoME.jar'):return shutil.copy2(src,dst)
    try:os.link(src,dst)
    except OSError:shutil.copy2(src,dst)
    return dst
with socket.socket() as probe:probe.bind(('127.0.0.1',0));port=probe.getsockname()[1]
with socket.socket() as probe:probe.bind(('127.0.0.1',0));spectator_port=probe.getsockname()[1]
proxy_port=port
if '--popup' in sys.argv:
    listener=socket.socket();listener.bind(('127.0.0.1',0));listener.listen(1);proxy_port=listener.getsockname()[1]
    def relay():
        client,_=listener.accept();upstream=socket.create_connection(('127.0.0.1',port))
        def pump(source,destination):
            try:
                for line in source.makefile('rb'):
                    if b' secureDiceReturn ' in line:time.sleep(.45)
                    destination.sendall(line)
            except OSError:pass
            finally:
                try:destination.shutdown(socket.SHUT_WR)
                except OSError:pass
        threading.Thread(target=pump,args=(client,upstream),daemon=True).start()
        pump(upstream,client)
    threading.Thread(target=relay,daemon=True).start()
procs=[]
for role in ('host','client','watch'):
    folder=sync/role
    shutil.copytree(work/'client',folder,copy_function=immutable_copy,ignore=shutil.ignore_patterns('logs','*.log','*.wotr','*-expected.txt'))
    log=(sync/(role+'.log')).open('w',encoding='utf8')
    args=[str(Path(os.environ.get('WOTR_TEST_JAVA','java'))),'-Dkome.mode=newWOTR:55','-Dtest.rollPopup='+str('--popup' in sys.argv).lower(),'-cp',str(work/'test-classes')+os.pathsep+'WOTR-GandalfV8-KoME.jar','KomeModernNetwork',role,str(proxy_port if role=='client' else port),str(spectator_port),str(sync)]
    procs.append((role,subprocess.Popen(args,cwd=folder,stdout=log,stderr=subprocess.STDOUT),log))
print('Network test:',sync,flush=True)
try:
    failed=False
    for role,proc,log in procs:
        code=proc.wait(timeout=85);log.close();output=(sync/(role+'.log')).read_text(encoding='utf8',errors='replace')
        print(role,code,'\n'.join(line for line in output.splitlines() if 'PASS' in line),flush=True)
        if code:print(output[-4000:],flush=True);failed=True
    if failed:raise SystemExit(1)
    p=subprocess.run([str(Path(os.environ.get('WOTR_TEST_JAVA','java'))),'-Dkome.mode=newWOTR:55','-cp',str(work/'test-classes')+os.pathsep+'WOTR-GandalfV8-KoME.jar','KomeModernReplay',str(sync)],cwd=work/'client',capture_output=True,text=True,timeout=40)
    (sync/'replay.log').write_text(p.stdout+p.stderr,encoding='utf8');print('Replay',p.returncode,'\n'.join(line for line in (p.stdout+p.stderr).splitlines() if 'PASS' in line),flush=True)
    raise SystemExit(p.returncode)
finally:
    for role,proc,log in procs:
        if proc.poll() is None:proc.kill()
        log.close()
