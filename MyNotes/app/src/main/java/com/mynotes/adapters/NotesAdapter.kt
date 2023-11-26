package com.mynotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mynotes.R
import com.mynotes.models.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = differ.currentList[position]
        val title = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        val data = holder.itemView.findViewById<TextView>(R.id.tvData)
        val content = holder.itemView.findViewById<TextView>(R.id.tvContent)
        holder.itemView.apply {
            title.text = note.title
            data.text = note.modificationDate
            content.text = note.content
            setOnClickListener{
                onItemClickListener?.let {
                    it(note)
                }
            }
        }
    }

    private var onItemClickListener : ((Note) -> Unit)? = null

    fun setOnItemClickListener(listener: (Note) -> Unit) {
        onItemClickListener = listener
    }

}