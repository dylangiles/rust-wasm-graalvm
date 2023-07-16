@echo off 
cargo build --target=wasm32-unknown-unknown --release
copy /B /V /Y .\target\wasm32-unknown-unknown\release\rust_wasm_graalvm.wasm .\rust_wasm_graalvm.wasm
kotlinc graalvm.kt -include-runtime -d rust_wasm_graalvm.jar
java -jar rust_wasm_graalvm.jar