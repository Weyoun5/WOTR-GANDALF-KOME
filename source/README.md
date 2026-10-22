# Client source and build

The original Java client is credited to Sean McCarthy, Andrew Poulter, and Peter Majek. Existing copyright notices remain applicable. See the repository credits and the original client README in `WOTR-GANDALF/README.pdf`. This source publication does not grant a new licence over upstream code or game assets.

`src/` contains the maintained Java extensions used in this release. `decompiled/` contains the original CFR listings for inspection; these are not the original authors' source, and decompiler artefacts and case-colliding class names prevent compiling them wholesale. The build preserves the original bytecode in `original/` and applies `tools/PatchKome.java` to integrate the extensions.

## Rebuild

Requires Python 3.10+ and JDK 21 (`java` and `javac` on PATH). Dependencies are included and checked against `dependencies.json`; no downloads are needed.

```
python source/build.py
```

Output: `source/.build/WOTR-GandalfV8-KoME.jar`, targeting Java 8. To run it, copy it into a separate copy of the shipped `WOTR-GANDALF` folder with all assets intact. The build leaves the distributed JAR and preferences untouched.

The gameplay identifier covers the original JAR, every maintained Java file, the patcher, and the bundled libraries. The release build date is pinned in `release.json`. ZIP timestamps may vary; uncompressed JAR entries should match the published runtime. Artwork preparation utilities are included for reference; rebuilding the Java client uses the shipped assets and does not require the original artwork workspace.

## Verify

```
python source/test.py
python source/test.py --network
```

The current suite covers 27 security and gameplay regressions. The optional network suite uses separate clients on localhost for native secure dice (including adversarial confirmations in base and expansion games), secure corruption, spectators, WoME cards, additional players, synchronization, and replay. Use `--java PATH` to select Java 8 explicitly while retaining JDK 21 for compilation. Tests require an interactive desktop, open Swing windows, and write only isolated copies under `.build/`. Other historical tests and diagnostic probes remain available in `tests/`; they are not all current release checks.

## Security scope

The original SHA-1 commitment algorithm and card algorithms remain unchanged. The native secure-dice responder now requires a non-null, exactly 32-character salt before checking the commitment. This closes the ambiguous salt/roll boundary bug in base and expansion games. KoME hidden Hunt tiles use encrypted identities, verified shuffle/opening proofs, and the native commit/reveal exchange for selection. Normal Hunt draws in KoME use that private pool after initialization.

These changes have regression and local-network coverage, not an independent cryptographic audit. Password protection depends on the Shadow password. Explicit file imports, synchronization and spectator history are trusted history; saved entropy receipts are not independently authenticated transcripts. A peer can abort. Public tabletop edits and undo remain available, and the protocol does not prove a hidden total remains below its threshold when resolution is withheld. Previously exposed information cannot become secret again through undo or hiding.
