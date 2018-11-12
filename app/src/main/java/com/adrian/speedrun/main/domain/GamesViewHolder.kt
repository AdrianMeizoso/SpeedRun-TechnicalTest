package com.adrian.speedrun.main.domain

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adrian.speedrun.R
import com.adrian.speedrun.databinding.GameItemBinding
import com.adrian.speedrun.main.domain.model.GameInfo

class GamesViewHolder(private val binding: GameItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): GamesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = GameItemBinding.inflate(layoutInflater, parent, false)
            return GamesViewHolder(itemBinding)
        }
    }

    fun bind(game: GameInfo) {
        binding.game = game
        binding.viewHolder = this
        binding.executePendingBindings()
    }

    fun onClickGame(view:View, game: GameInfo) {
        val bundleGame = bundleOf("game" to game.id)
        view.findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundleGame)
    }

    fun clear() {

    }
}