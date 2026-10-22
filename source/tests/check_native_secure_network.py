from pathlib import Path
import subprocess,shutil,os,socket,time
work=Path(os.environ['WOTR_TEST_WORK'])
java=Path(os.environ.get('WOTR_TEST_JAVA','java'))

def copy(src,dst):
    if Path(src).name in ('wotr_preferences','appearance.properties','WOTR-GandalfV8-KoME.jar'):return shutil.copy2(src,dst)
    try:os.link(src,dst)
    except OSError:shutil.copy2(src,dst)
    return dst

for mask in (0,55):
    sync=work/('native-network-'+str(int(time.time()))+'-'+str(mask));sync.mkdir()
    with socket.socket() as probe:probe.bind(('127.0.0.1',0));port=probe.getsockname()[1]
    procs=[]
    try:
        for role in ('host','client'):
            folder=sync/role;shutil.copytree(work/'client',folder,copy_function=copy,ignore=shutil.ignore_patterns('logs','*.log','*.wotr'))
            log=(sync/(role+'.log')).open('w',encoding='utf8')
            proc=subprocess.Popen([str(java),'-Dkome.mode=newWOTR:'+str(mask),'-cp',str(work/'test-classes')+os.pathsep+'WOTR-GandalfV8-KoME.jar','KomeNativeSecureNetwork',role,str(port),str(sync)],cwd=folder,stdout=log,stderr=subprocess.STDOUT)
            procs.append((role,proc,log))
        print('Native network test:',sync,flush=True)
        failed=False
        for role,proc,log in procs:
            code=proc.wait(timeout=90);log.close();output=(sync/(role+'.log')).read_text(encoding='utf8',errors='replace')
            print(mask,role,code,'\n'.join(s for s in output.splitlines() if 'PASS' in s),flush=True)
            if code:print(output[-3000:],flush=True);failed=True
        if failed:raise SystemExit(1)
    finally:
        for _,proc,log in procs:
            if proc.poll() is None:proc.kill()
            log.close()
