package com.example.aslikuterdem_hw1_487

class Piece(
   public val level: String = "Beginner",
   public val instr: String = "Piano",
   public val piece_name: String = "Arabseque",
   public val era: String = "Classical",
) {
    fun getString(): String {
        return """
            Piece Name: $piece_name
            Era: $era
            Level: $level
            Instrument: $instr
        """
    }
}
