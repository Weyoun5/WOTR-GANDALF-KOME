"""Exercise real mixed-version JVMs in both host/client directions and modes."""
from pathlib import Path
import subprocess, shutil, socket, time, sys

dev=Path(__file__).resolve().parents[1]
app=dev.parent/'WOTR-GANDALF'
old=dev/'compatibility/v1.0.1/WOTR-GandalfV8-KoME.jar'
release_dir=Path(sys.argv[1]).resolve() if len(sys.argv)>1 else None
base=dev/('crossplay-'+str(int(time.time())))
base.mkdir()

def reference(path):
    return path.startswith('kome/characters/') or path in ['kome/KOME_rules.pdf','kome/KOME_rules.txt']

for mode in ['newKOME','newKOME[L]']:
    for old_role in ['host','client']:
        run=base/(('lome' if '[L]' in mode else 'kings')+'-old-'+old_role)
        run.mkdir()
        with socket.socket() as probe:
            probe.bind(('127.0.0.1',0));port=probe.getsockname()[1]
        processes=[]
        for role in ['host','client']:
            folder=run/role;folder.mkdir()
            # Exact packaged files, with reference assets absent on the new client.
            from zipfile import ZipFile
            version='v1.0.1' if role==old_role else 'v1.0.2'
            archive=release_dir/version/'updated/WOTR-KoME.zip' if release_dir else dev.parent/'WOTR-KoME.zip'
            with ZipFile(archive) as package:
                for entry in package.infolist():
                    relative=entry.filename.removeprefix('WOTR-GANDALF/')
                    if entry.is_dir() or (role!=old_role and reference(relative)):continue
                    destination=folder/relative;destination.parent.mkdir(parents=True,exist_ok=True)
                    destination.write_bytes(package.read(entry.filename))
            if role==old_role and not release_dir:
                # Working source art remains available after the new ZIP omits it.
                shutil.copytree(app/'kome/characters',folder/'kome/characters',dirs_exist_ok=True)
                for name in ['KOME_rules.pdf','KOME_rules.txt']:
                    shutil.copy2(app/'kome'/name,folder/'kome'/name)
            if not release_dir:shutil.copy2(old if role==old_role else app/'WOTR-GandalfV8-KoME.jar',folder/'WOTR-GandalfV8-KoME.jar')
            (folder/'logs/auto').mkdir(parents=True,exist_ok=True)
            (folder/'wotr_preferences').write_text(f'nick:Crossplay_{role}\nzoom:80\nzoomLevels:80,100,120\nwinx:1280\nwiny:850\nlastGame:base2\nshowIPonstart:false\nnopassword:true\n',encoding='utf8')
            log=(run/(role+'.log')).open('w')
            cmd=['java','-Dkome.mode='+mode,'-Dkome.testDiceActions='+str(bool(release_dir)).lower(),'-cp',str(dev/'test-classes')+';WOTR-GandalfV8-KoME.jar','KomeNetwork',role,str(port),str(run)]
            processes.append((role,subprocess.Popen(cmd,cwd=folder,stdout=log,stderr=subprocess.STDOUT),log))
        try:
            for role,process,log in processes:
                code=process.wait(timeout=65);log.close()
                output=(run/(role+'.log')).read_text()
                for line in output.splitlines():
                    if 'NETWORK PASS' in line:print(run.name, 'v1.0.1' if role==old_role else 'v1.0.2',line,flush=True)
                if code:raise RuntimeError(f'Crossplay failed: {run}/{role}.log')
        finally:
            for role,process,log in processes:
                if process.poll() is None:process.kill()
                log.close()
print('CROSSPLAY PASS: both modes, v1.0.1 host/v1.0.2 client and reverse;',base,flush=True)
