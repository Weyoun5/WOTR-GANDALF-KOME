from pathlib import Path
import subprocess, shutil, socket, sys, time

dev=Path(__file__).resolve().parents[1]
app=dev.parent/'WOTR-GANDALF'
test=dev/'test-client'
subprocess.run(['javac','--release','8','-Xlint:-options','-encoding','UTF8','-cp',str(app/'WOTR-GandalfV8-KoME.jar'),'-d',str(dev/'test-classes'),*[str(p) for p in (dev/'tests').glob('*.java')]],check=True)

def prefs(folder, mode='base2', nick='KoME_Test'):
    (folder/'wotr_preferences').write_text(f'nick:{nick}\nzoom:80\nzoomLevels:80,100,120\nwinx:1280\nwiny:850\nlastGame:{mode}\nshowIPonstart:false\nnopassword:true\n',encoding='utf8')

def run(name, cls, jar='WOTR-GandalfV8-KoME.jar', mode=None):
    cmd=['java']+([f'-Dkome.mode={mode}'] if mode else [])+['-cp',str(dev/'test-classes')+';'+jar,cls]
    with (test/(name+'.log')).open('w') as log:
        subprocess.run(cmd,cwd=test,stdout=log,stderr=subprocess.STDOUT,timeout=35,check=True)
    result=[s for s in (test/(name+'.log')).read_text().splitlines() if ' PASS ' in s or ' SNAPSHOT ' in s]
    print(name,':','; '.join(result),flush=True)
    return result

prefs(test)
if '--network-only' not in sys.argv:
    for mode in ['base2','newLOME']:
        prefs(test,mode)
        run('inventory-'+mode,'DeckInventory',str(dev/'original/WOTR-GandalfV8.jar'))
    prefs(test)
    run('menu','KomeMenu')
    prefs(test)
for mode in ([] if '--network-only' in sys.argv else ['newKOME','newKOME[L]']):
    suffix='lome' if '[L]' in mode else 'kings'
    run('smoke-'+suffix,'KomeSmoke',mode=mode)
    run('replay-'+suffix,'KomeReplay',mode=mode)
    run('cards-'+suffix,'KomeCards',mode=mode)
    run('hunt-privacy-'+suffix,'KomeHuntPrivacy',mode=mode)
    run('leadership-'+suffix,'KomeLeadership',mode=mode)
    run('dice-actions-'+suffix,'KomeDiceActions',mode=mode)
    run('combat-ui-'+suffix,'KomeCombatUi',mode=mode)
    run('tab-ui-'+suffix,'KomeTabUi',mode=mode)
    run('setup-ui-'+suffix,'KomeSetupUi',mode=mode)
    run('reserves-'+suffix,'KomeReserves',mode=mode)
    run('appearance-'+suffix,'KomeAppearance',mode=mode)
    if mode=='newKOME[L]':run('legacy-replay','KomeLegacyReplay',mode=mode)
    if mode=='newKOME':run('load-guard','KomeLoadGuard',mode=mode)
for mode in ([] if '--network-only' in sys.argv else ['base2','newLOME']):
    prefs(test,mode)
    original=run('original-'+mode,'BaseSmoke',str(dev/'original/WOTR-GandalfV8.jar'))
    patched=run('patched-'+mode,'BaseSmoke')
    assert original==patched,(original,patched)
prefs(test)

for mode in ['newKOME','newKOME[L]']:
    suffix='lome' if '[L]' in mode else 'kings'
    sync=dev/('network-'+suffix+'-'+str(int(time.time())))
    sync.mkdir()
    with socket.socket() as probe:
        probe.bind(('127.0.0.1',0));port=probe.getsockname()[1]
    procs=[]
    for role in ['host','client']:
        folder=dev/('network-'+role)
        folder.mkdir(exist_ok=True)
        # Reuse game art without altering the original application's preferences/logs.
        for source in test.iterdir():
            if source.is_dir() and source.name!='logs':
                shutil.copytree(source,folder/source.name,dirs_exist_ok=True)
            elif source.name in ['messages.properties','WOTR-GandalfV8-KoME.jar']:
                shutil.copy2(source,folder/source.name)
        (folder/'logs'/'auto').mkdir(parents=True,exist_ok=True)
        prefs(folder,nick='KoME_'+role)
        log=(sync/(role+'.log')).open('w')
        command=['java',f'-Dkome.mode={mode}','-Dkome.testDiceActions=true','-cp',str(dev/'test-classes')+';WOTR-GandalfV8-KoME.jar','KomeNetwork',role,str(port),str(sync)]
        procs.append((role,subprocess.Popen(command,cwd=folder,stdout=log,stderr=subprocess.STDOUT),log))
    try:
        for role,proc,log in procs:
            code=proc.wait(timeout=65);log.close()
            output=(sync/(role+'.log')).read_text()
            print('\n'.join(line for line in output.splitlines() if 'NETWORK PASS' in line),flush=True)
            if code:raise RuntimeError(f'Network {suffix}/{role} failed; see {sync}')
    finally:
        for role,proc,log in procs:
            if proc.poll() is None:proc.kill()
            log.close()
print('ALL TESTS PASSED',flush=True)
