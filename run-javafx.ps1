param(
    [string]$JavaFXLib = $env:JAVA_FX_LIB
)

if (-not $JavaFXLib) {
    Write-Host "ERROR: Set environment variable `"JAVA_FX_LIB`" to the JavaFX SDK 'lib' folder (e.g. C:\\javafx-sdk-20\\lib)." -ForegroundColor Red
    exit 1
}

$OutDir = "out"
if (-not (Test-Path $OutDir)) { New-Item -ItemType Directory -Path $OutDir | Out-Null }

$javaFiles = Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName
if (-not $javaFiles) {
    Write-Host "No Java source files found. Make sure you're running this from the repository root." -ForegroundColor Yellow; exit 1
}

Write-Host "Compiling Java sources..."
& javac --module-path "$JavaFXLib" --add-modules javafx.controls,javafx.fxml -d $OutDir $javaFiles
if ($LASTEXITCODE -ne 0) { Write-Host "Compilation failed" -ForegroundColor Red; exit $LASTEXITCODE }

Write-Host "Running JavaFX application..."
& java --module-path "$JavaFXLib" --add-modules javafx.controls,javafx.fxml -cp $OutDir wardrobe.Runner
