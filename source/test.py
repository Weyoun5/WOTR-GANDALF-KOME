"""Run current regression checks on a scratch runtime. Requires a desktop."""
from pathlib import Path
import argparse,json,os,shutil,subprocess,tempfile
import build

source=Path(__file__).resolve().parent
parser=argparse.ArgumentParser()
parser.add_argument('--network',action='store_true',help='Also run player/spectator/WoME network and replay checks')
parser.add_argument('--java',default='java',help='Runtime executable (Java 8 or newer)')
args=parser.parse_args()
jar=build.main()
work=Path(tempfile.mkdtemp(prefix='verification-',dir=source/'.build'))
app=work/'client';classes=work/'test-classes';classes.mkdir()
shutil.copytree(source.parent/'WOTR-GANDALF',app,ignore=shutil.ignore_patterns('logs','*.log','*.wotr','wotr_preferences','appearance.properties'))
shutil.copy2(jar,app/jar.name)
cases=[('KomeHuntCryptoTest', 55), ('KomePrivateHuntAttackTest', 55), ('KomePrivateHuntFlowTest', 55), ('KomePrivateResolutionTest', 55), ('KomeBaseIsolationTest', 0), ('KomeCommandSecurityTest', 55), ('KomeCorruptionTest', 55), ('KomeLeadership', 55), ('KomeDiceActions', 55), ('KomeCombatUi', 55), ('WomeRulesTest', 55), ('KomeCombinedHandsTest', 55), ('KomeVersionWireTest', 55), ('KomeCorruptionOptionsTest', 55), ('KomeZoomTest', 55), ('KomeZoomTest', 0), ('KomeRotkTest', 0, '0'), ('KomeRotkTest', 55, '55'), ('KomeThreadingChecks', 55), ('KomeAudioQualityTest', 55), ('KomeAudioSettingsTest', 55), ('KomeEventAudioTest', 55), ('KomePrivateDisconnectTest', 55)]
names=set(c[0] for c in cases)
if args.network:names.update(['KomeModernNetwork','KomeModernReplay','WomeNetwork','WomeReplay','KomeAdditionalNetwork','KomeAdditionalReplay'])
subprocess.run(['javac','--release','8','-encoding','UTF-8','-cp',str(jar),'-sourcepath',str(source/'tests'),'-d',str(classes),*[str(source/'tests'/(n+'.java')) for n in sorted(names)]],check=True)
results=[]
for name,mask,*extra in cases:
    with (work/(name+'-'+str(mask)+'.log')).open('w',encoding='utf8') as log:
        result=subprocess.run([args.java,'-Dkome.mode=newWOTR:'+str(mask),'-cp',os.pathsep.join([str(classes),str(jar)]),name,*extra],cwd=app,stdout=log,stderr=subprocess.STDOUT,timeout=90)
    print(name,mask,'PASS' if result.returncode==0 else 'FAIL',flush=True)
    results.append({'test':name,'mask':mask,'exit_code':result.returncode})
(work/'results.json').write_text(json.dumps(results,indent=2),encoding='utf8')
assert all(r['exit_code']==0 for r in results),'Regression failed; see '+str(work)
if args.network:
    env=dict(os.environ,WOTR_TEST_WORK=str(work),WOTR_TEST_JAVA=args.java)
    for name in ('check_modern_network.py','check_wome_network.py','check_additional_network.py'):
        subprocess.run([__import__('sys').executable,str(source/'tests'/name)],env=env,check=True)
print('All requested checks passed. Logs:',work)
