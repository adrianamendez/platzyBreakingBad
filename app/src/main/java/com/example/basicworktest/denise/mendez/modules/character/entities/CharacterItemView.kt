package com.example.basicworktest.denise.mendez.modules.character.entities

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.example.basicworktest.denise.mendez.R
import com.example.basicworktest.denise.mendez.adapter.ItemView
import com.example.basicworktest.denise.mendez.databinding.ItemCharacterBinding
import com.example.basicworktest.denise.mendez.utils.setSingleClickListener
import com.example.basicworktest.denise.mendez.view.properties.itemViewBinding

class CharacterItemView(
    override val context: Context,
    onCardClickListener: ((CharacterBreakingBadUi) -> Unit),
    onFavouriteClickListener: ((CharacterBreakingBadUi) -> Unit),
) : ItemView<CharacterBreakingBadUi> {

    private val binding by itemViewBinding<ItemCharacterBinding>(R.layout.item_character)

    override val view = binding.root

    override lateinit var data: CharacterBreakingBadUi

    init {
        binding.root.apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            )
            binding.cardViewTokenHome.setSingleClickListener {
                onCardClickListener(data)
            }
            binding.favouriteImageView.setSingleClickListener {
                onFavouriteClickListener(data)
            }
        }
    }

    override fun bind(item: CharacterBreakingBadUi) {
        data = item
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}