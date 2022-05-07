package com.example.sozlukmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sozlukmvvm.databinding.WordCardBinding
import com.example.sozlukmvvm.model.Kelimeler

class WordAdapter :RecyclerView.Adapter<WordAdapter.WordCardHolder>(){

    private val wordList= arrayListOf<Kelimeler>()
    var onClick:(Kelimeler)->Unit={}

    class WordCardHolder(private val wordCardBinding: WordCardBinding):RecyclerView.ViewHolder(wordCardBinding.root){
        fun find(word:Kelimeler,onClick:(Kelimeler)->Unit={}){
            with(wordCardBinding){
                wordCardBinding.word=word
                executePendingBindings()
                card.setOnClickListener {
                    onClick(word)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCardHolder {
        val layout=WordCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WordCardHolder(layout)
    }

    override fun onBindViewHolder(holder: WordCardHolder, position: Int) {
        val word=wordList[position]
        holder.find(word,onClick)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun updateList(list:List<Kelimeler>){
        wordList.clear()
        wordList.addAll(list)
        notifyDataSetChanged()
    }
}