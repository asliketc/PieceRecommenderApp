package com.example.aslikuterdem_hw1_487

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aslikuterdem_hw1_487.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val pieceName = result.data?.getStringExtra("piece_name")
            if (!pieceName.isNullOrEmpty()) {
                binding.tvchose.text = pieceName
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.rbflute.setOnClickListener{
            binding.ivinstr.setImageResource(R.drawable.flute)
        }
        binding.rbpiano.setOnClickListener {
            binding.ivinstr.setImageResource(R.drawable.piano)
        }
        binding.rbviolin.setOnClickListener {
            binding.ivinstr.setImageResource(R.drawable.violin)
        }

        binding.btncont.setOnClickListener{
            val intr=when{
                binding.rbflute.isChecked->"Flute"
                binding.rbpiano.isChecked->"Piano"
                binding.rbviolin.isChecked->"Violin"
                else->""
            }
            makeToast("$intr is selected")
            if(intr.isEmpty()){
                makeShowDialog("Fill Instrument Section")
            }else if(binding.etname.text.toString().isEmpty()){
                sackBar("Fill Name Area")
            }
            else{
                val intent= Intent(this,SecondActivity::class.java)
                intent.putExtra("instrument",intr)
                intent.putExtra("name",binding.etname.text.toString())
                startForResult.launch(intent)
            }


        }

    }




    private fun makeToast(msg:String){
        Toast.makeText(this,"$msg is Your Instrument",Toast.LENGTH_SHORT).show()
    }
    private fun makeShowDialog(msg:String?){
        AlertDialog.Builder(this).setTitle("ERROR")
            .setMessage(msg)
            .setPositiveButton("Close"){dialog,_->dialog.dismiss()}
            .create()
            .show()
    }
    private fun sackBar(msg:String){
        Snackbar.make(binding.root,msg,Snackbar.LENGTH_SHORT).show()
    }
}
