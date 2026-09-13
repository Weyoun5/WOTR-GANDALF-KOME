@echo off
cd /d "%~dp0"
if not exist "logs\auto" mkdir "logs\auto"
start "" javaw -jar "WOTR-GandalfV8-KoME.jar"
