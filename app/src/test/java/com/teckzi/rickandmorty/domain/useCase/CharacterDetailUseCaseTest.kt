package com.teckzi.rickandmorty.domain.useCase

//
//@ExperimentalCoroutinesApi
//class CharacterDetailUseCaseTest {
//
//    @MockK
//    lateinit var repository: IRepository
//
//    lateinit var useCase: GetSelectedCharacterUseCase
//
//    @BeforeEach
//    fun setUp() {
//        MockKAnnotations.init(this)
//        useCase = GetSelectedCharacterUseCase(repository)
//    }
//
//    @Test
//    fun `given successful response, when getCharacterById called, return character`() {
//        runTest {
//            val id = 30
//            val response = CharacterDto(
//                30,
//                "",
//                "",
//                "",
//                "",
//                "",
//                Origin("unknown", ""),
//                Origin("unknown", ""),
//                "",
//                listOf(""),
//                "",
//                ""
//            )
//
//            coEvery { repository.getCharacterById(id) } answers { response.toCharacterModel() }
//
//            useCase.invoke(id)
//
//            coVerify { repository.getCharacterById(id) }
//        }
//    }
//}