package com.teckzi.rickandmorty.domain.useCase


//class GetCharactersUseCaseTest {
//
//    private val repository = mockk<Repository>()
//    private val useCase = GetCharacterListById(repository)
//
//    @Test
//    fun `given character list, when invoke, then return same list`() =
//        runTest {
//            val fakeCharacters = testCharacters
//            stubRepository(fakeCharacters)
//
//            val characters = useCase.invoke(listOf(1, 2, 3))
//
//            assert(characters == fakeCharacters)
//        }
//
//    private fun stubRepository(characters: List<CharacterModel>) {
//        coEvery { repository.getCharacterListById(listOf(1, 2, 3)) } coAnswers {
//            characters
//        }
//    }
//}