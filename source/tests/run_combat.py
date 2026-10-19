from pathlib import Path
import subprocess, shutil, socket, time

dev=Path(__file__).resolve().parents[1]
base=dev/('combat-network-'+str(int(time.time())))
base.mkdir()
for mode in ['newKOME','newKOME[L]']:
    sync=base/('lome' if '[L]' in mode else 'kings');sync.mkdir()
    with socket.socket() as probe:probe.bind(('127.0.0.1',0));port=probe.getsockname()[1]
    processes=[]
    for role in ['host','client']:
        folder=sync/role;folder.mkdir()
        for source in (dev/'test-client').iterdir():
            if source.is_dir() and source.name!='logs':shutil.copytree(source,folder/source.name,dirs_exist_ok=True)
            elif source.name in ['messages.properties','WOTR-GandalfV8-KoME.jar']:shutil.copy2(source,folder/source.name)
        (folder/'logs/auto').mkdir(parents=True)
        (folder/'wotr_preferences').write_text(f'nick:Combat_{role}\nzoom:80\nzoomLevels:80,100,120\nwinx:1280\nwiny:850\nlastGame:base2\nshowIPonstart:false\nnopassword:true\n')
        log=(sync/(role+'.log')).open('w')
        command=['java','-Dkome.mode='+mode,'-cp',str(dev/'test-classes')+';WOTR-GandalfV8-KoME.jar','KomeCombatNetwork',role,str(port),str(sync)]
        processes.append((role,subprocess.Popen(command,cwd=folder,stdout=log,stderr=subprocess.STDOUT),log))
    try:
        for role,process,log in processes:
            code=process.wait(timeout=60);log.close()
            output=(sync/(role+'.log')).read_text()
            print('\n'.join(line for line in output.splitlines() if 'PASS' in line or 'Error' in line or 'Exception' in line),flush=True)
            if code:raise RuntimeError(str(sync/role))
    finally:
        for role,process,log in processes:
            if process.poll() is None:process.kill()
            log.close()
print('COMBAT NETWORK TESTS PASSED',base,flush=True)
