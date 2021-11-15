package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class GetFixtureForAlarmUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(fixtureId: Int) = repository.getFixtureForAlarm(fixtureId)
}