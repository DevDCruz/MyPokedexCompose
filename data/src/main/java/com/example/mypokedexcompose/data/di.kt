import com.example.mypokedexcompose.data.dataSource.repository.BackPackItemRepository
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.data.region.RegionRepository
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataPokemonModule = module {
    factoryOf(::PokemonRepository) bind IPokemonRepository::class
}

val dataPokedexModule = module {
    factoryOf(::PokedexRepository) bind IPokedexRepository::class
}

val dataBerryModule = module {
    factoryOf(::BerryRepository) bind IBerryRepository::class
}

val dataBackPackModule = module {
    factoryOf(::BackPackItemRepository) bind IBackPackItemRepository::class
}

val dataRegionModule = module {
    factoryOf(::RegionRepository)
}
