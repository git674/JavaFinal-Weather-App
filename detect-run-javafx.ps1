param(
    [string]$LibPath
)

function Prompt-ForLib {
    param($message)
    Write-Host $message -ForegroundColor Yellow
    return Read-Host "Enter path to JavaFX SDK lib folder (e.g. C:\\javafx-sdk-20\\lib)"
}

# Try provided path, env var, and common locations
if (-not $LibPath) { $LibPath = $env:JAVA_FX_LIB }

if (-not $LibPath) {
    $candidates = @(
        'C:\javafx-sdk*',
        'C:\Program Files\javafx-sdk*',
        'C:\Program Files\Java\javafx-sdk*',
        "$env:USERPROFILE\\javafx-sdk*",
        '.\\javafx-sdk*'
    )
    foreach ($p in $candidates) {
        $dirs = Get-ChildItem -Path $p -Directory -ErrorAction SilentlyContinue
        if ($dirs) { $LibPath = ($dirs | Select-Object -First 1).FullName + '\\lib'; break }
    }
}

if (-not $LibPath -or -not (Test-Path $LibPath)) {
    $LibPath = Prompt-ForLib "JavaFX lib folder not auto-detected."
}

if (-not (Test-Path $LibPath)) {
    Write-Host "ERROR: The provided path does not exist: $LibPath" -ForegroundColor Red
    exit 1
}

Write-Host "Using JavaFX lib folder: $LibPath" -ForegroundColor Green

# Show contents (common jars you should see)
Write-Host "Contents of JavaFX lib (showing .jar files):" -ForegroundColor Cyan
Get-ChildItem -Path $LibPath -Filter *.jar | Sort-Object Name | ForEach-Object { Write-Host " - " $_.Name }

# Set environment variable for this session
$env:JAVA_FX_LIB = $LibPath

# Compile sources (Wardrobe and UI are in src/main/java/wardrobe)
if (-not (Test-Path out)) { New-Item -ItemType Directory out | Out-Null }

$sources = Get-ChildItem -Path src\main\java\wardrobe -Filter *.java -Recurse | ForEach-Object { $_.FullName }
if (-not $sources) {
    Write-Host "No Java sources found in src/main/java/wardrobe. Make sure you run this from the repo root." -ForegroundColor Red
    exit 1
}

Write-Host "Compiling Java sources..." -ForegroundColor Green
javac --module-path "$LibPath" --add-modules javafx.controls,javafx.fxml -d out $sources
if ($LASTEXITCODE -ne 0) { Write-Host "Compilation failed." -ForegroundColor Red; exit $LASTEXITCODE }

Write-Host "Running JavaFX application..." -ForegroundColor Green
java --module-path "$LibPath" --add-modules javafx.controls,javafx.fxml -cp out wardrobe.Main
