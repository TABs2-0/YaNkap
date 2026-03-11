package org.example.yankap

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform