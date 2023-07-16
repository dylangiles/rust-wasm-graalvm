package rust_wasm_graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.io.ByteSequence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Rust {
    private val evaluatedSource: Value;
    init {
        val WASM_LOCATION = "rust_wasm_graalvm.wasm"
        val binary = Files.readAllBytes(Paths.get(WASM_LOCATION))
        val contextBuilder = Context.newBuilder("wasm")
        val sourceBuilder = Source.newBuilder("wasm", ByteSequence.create(binary), "rust")
        val source = sourceBuilder.build()
        val context = contextBuilder.build()
        evaluatedSource = context.eval(source)
    }

    fun add(left: Int, right: Int): Int {
        return evaluatedSource.getMember("add").execute(left, right).asInt()
    }
}

fun main() {
    val rust = Rust()
    println(rust.add(40, 2))
}