package screens.details

import domain.models.SpellDetail
import domain.repositories.SpellDetailRepository
import org.koin.core.component.KoinComponent

class DetailsViewModel(
    private val spellDetailRepository: SpellDetailRepository
) : KoinComponent {
    
}