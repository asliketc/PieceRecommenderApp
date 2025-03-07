package com.example.aslikuterdem_hw1_487

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.aslikuterdem_hw1_487.databinding.ActivityMainBinding
import com.example.aslikuterdem_hw1_487.databinding.ActivityThirdBinding
import com.example.aslikuterdem_hw1_487.databinding.CustomDialogBinding


class ThirdActivity: AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    private lateinit var dialogBinding:CustomDialogBinding
    private lateinit var customDialog:Dialog
    private lateinit var sval:String
    private lateinit var pieces:List<Piece>
    private lateinit var selectedPiece:Piece

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        prepareData()
        val lvl=intent.getStringExtra("lvl") ?: ""
        val era=intent.getStringExtra("era")?:""
        val ins=intent.getStringExtra("instr")?:""

       selectedPiece=pieces.filter{
            it.level==lvl && it.era==era && it.instr==ins
        }.firstOrNull() ?:Piece()

        binding.tvrecommend.text=selectedPiece.piece_name


        binding.sb.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               when(progress){
                   0->binding.imageView.setImageResource(R.drawable.not)
                   1->binding.imageView.setImageResource(R.drawable.not)
                   2-> binding.imageView.setImageResource(R.drawable.meh)
                   else->binding.imageView.setImageResource(R.drawable.happy)
               }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
        
        binding.btnback.setOnClickListener { 
            val resultIntent= Intent()
            resultIntent.putExtra("piece_name",selectedPiece.piece_name)
            setResult(RESULT_OK,resultIntent)
            finish()
        }

        binding.btnshowdetails.setOnClickListener{
            detailDialogBuild(lvl,ins,era,selectedPiece.piece_name)
        }
    }
    private fun prepareData(){
        pieces=listOf(
            Piece(level="Beginner",instr="Piano",piece_name="Air in G String(transc)",era="Baroque"),
            Piece(level="Intermediate",instr="Piano",piece_name="Prelude an Fugue in D by Bach",era="Baroque"),
            Piece(level="Advanced",instr="Piano",piece_name="French Suite No.2 by Bach",era="Baroque"),
            Piece(level="Intermediate",instr="Piano",piece_name="Moonlight Sonata Mvmnt.1-Beethoven",era="Classical"),
            Piece(level="Advanced",instr="Piano", piece_name = "Piano Concerto No 4-Beethoven",era="Classical"),
            Piece(level="Beginner",instr="Piano",piece_name="Prelude No.1 by Chopin",era="Romantic"),
            Piece(level="Intermediate",instr="Piano", piece_name = "Consolation No.3 by Liszt",era="Romantic"),
            Piece(level="Advanced",instr="Piano",piece_name="Etude No.4 by Rachmaninov",era="Romantic"),
            Piece(level="Beginner",instr="Violin",piece_name="Concerto in A by Vivaldi",era="Baroque"),
            Piece(level="Intermediate",instr="Violin", piece_name = "Gigue by Bach",era="Baroque"),
            Piece(level="Advanced",instr="Violin",piece_name="Ciaconne by Bach",era="Baroque"),
            Piece(level="Beginner",instr="Violin", piece_name = "Sonatina by Haydn",era="Classical"),
            Piece(level="Intermediate",instr="Violin",piece_name="Concerto No.2 by Mozart",era="Classical"),
            Piece(level="Advanced","Violin","Spring Sonata by Beethoven",era="Classical"),
            Piece("Beginner","Violin","Melody in C","Romantic"),
            Piece("Intermediate","Violin","Concerto in E Minor by Mendelssohn","Romantic"),
            Piece("Advanced","Violin","Concerto in D Major by Tchaikovsky","Romantic"),
            Piece("Beginner","Flute","Concerto in C by Telemann","Baroque"),
            Piece("Intermediate","Flute","Etude in A","Baroque"),
            Piece("Advanced","Flute","Suite for Flute by Bach","Baroque"),
            Piece("Beginner","Flute","Sonatina by Clementine","Classical"),
            Piece("Intermediate","Flute","Concerto for Harp & Flute by Mozart","Classical"),
            Piece("Advanced","Flute","Excerpt from Haydn Surprise Sonata","Classical"),
            Piece("Beginner","Flute","Transc. of Chopin Nocturne No.20","Romantic"),
            Piece("Intermediate","Flute","Excerpt from Symphony Fantastique","Romantic"),
            Piece("Advanced","Flute","Masqerade by Debussy","Romantic")
        )
    }

    private fun detailDialogBuild( level:String,instr:String,era:String, nm:String){
        val dialog=Dialog(this)
        dialogBinding=CustomDialogBinding.inflate(layoutInflater)
        customDialog=Dialog(this).apply {
            setContentView(dialogBinding.root)
        }
        dialogBinding.tvdet.text="Piece Name: $nm\nLevel: $level\nEra: $era\nFor Instrument: $instr"
        customDialog.show()

    }
}


