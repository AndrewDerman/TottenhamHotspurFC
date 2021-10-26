package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class GetFixtureInfoUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(fixtureId: Int) = repository.getFixtureInfo(fixtureId)
}