from pathlib import Path
import subprocess,shutil,os,socket,time
work=Path(os.environ['WOTR_TEST_WORK'])
sync=work/('additional-network-'+str(int(time.time())));sync.mkdir()
java=Path(os.environ.get('WOTR_TEST_JAVA','java'))
def copy(src,dst):
    if Path(src).name in ('wotr_preferences','WOTR-GandalfV8-KoME.jar'):return shutil.copy2(src,dst)
    try:os.link(src,dst)
    except OSError:shutil.copy2(src,dst)
    return dst
def port():
    with socket.socket() as s:s.bind(('127.0.0.1',0));return s.getsockname()[1]
native,stream=port(),port();processes=[]
try:
    for role in ('host','client','watch','extra3','extra4'):
        folder=sync/role;shutil.copytree(work/'client',folder,copy_function=copy,ignore=shutil.ignore_patterns('logs','*.log','*.wotr','*-expected.txt'))
        log=(sync/(role+'.log')).open('w',encoding='utf-8');args=[str(java),'-Dkome.mode=newWOTR:55','-cp',str(work/'test-classes')+os.pathsep+'WOTR-GandalfV8-KoME.jar','KomeAdditionalNetwork',role,str(native),str(stream),str(sync)]
        processes.append((role,subprocess.Popen(args,cwd=folder,stdout=log,stderr=subprocess.STDOUT),log))
    print('Testing:',sync,flush=True);failed=False
    for role,p,log in processes:
        rc=p.wait(timeout=100);log.close();output=(sync/(role+'.log')).read_text(encoding='utf-8',errors='replace');print(role,rc,flush=True)
        if rc:print('\n'.join(line for line in output.splitlines() if 'Exception' in line or 'Error' in line or line.startswith('\tat '))[-3000:],flush=True);failed=True
        else:print('\n'.join(line for line in output.splitlines() if 'PASS' in line),flush=True)
    if not failed:
        p=subprocess.run([str(java),'-Dkome.mode=newWOTR:55','-cp',str(work/'test-classes')+os.pathsep+'WOTR-GandalfV8-KoME.jar','KomeAdditionalReplay',str(sync)],cwd=work/'client',capture_output=True,text=True,timeout=40)
        (sync/'replay.log').write_text(p.stdout+p.stderr,encoding='utf-8');print('Replay',p.returncode,'\n'.join(line for line in p.stdout.splitlines() if 'PASS' in line),flush=True)
        if p.returncode:print((p.stdout+p.stderr)[-3000:]);failed=True
    raise SystemExit(1 if failed else 0)
finally:
    for role,p,log in processes:
        if p.poll() is None:p.kill()
        log.close()
