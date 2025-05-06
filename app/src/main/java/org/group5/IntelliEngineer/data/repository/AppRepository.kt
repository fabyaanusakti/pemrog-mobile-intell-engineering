package org.group5.IntelliEngineer.data.repository

import org.group5.IntelliEngineer.data.auth.dao.UserDao
import org.group5.IntelliEngineer.data.auth.entity.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.getUser(username, password)
    }

    suspend fun checkUsernameExists(username: String): Boolean {
        return userDao.doesUsernameExist(username)
    }

}