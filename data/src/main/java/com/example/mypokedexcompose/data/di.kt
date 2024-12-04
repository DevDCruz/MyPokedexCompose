import com.example.mypokedexcompose.data.dataSource.repository.BackPackItemRepository
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.data.region.RegionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataPokemonModule = module {
    factoryOf(::PokemonRepository)
}

val dataPokedexModule = module {
    factoryOf(::PokedexRepository)
}

val dataBerryModule = module {
    factoryOf(::BerryRepository)
}

val dataBackPackModule = module {
    factoryOf(::BackPackItemRepository)
}

val dataRegionModule = module {
    factoryOf(::RegionRepository)
}

val dataModules = listOf(
    dataPokemonModule,
    dataPokedexModule,
    dataBerryModule,
    dataBackPackModule,
    dataRegionModule
)