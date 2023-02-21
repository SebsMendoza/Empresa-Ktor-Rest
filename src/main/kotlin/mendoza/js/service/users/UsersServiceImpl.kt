package mendoza.js.service.users

import kotlinx.coroutines.flow.Flow
import mendoza.js.models.User
import mendoza.js.repositories.users.UsuarioRepository
import mu.KotlinLogging
import org.koin.core.annotation.Single
import java.time.LocalDateTime
import java.util.UUID

private val logger = KotlinLogging.logger { }

@Single
class UsuarioServiceImpl(
    private val usuarioRepository: UsuarioRepository
) : UsuarioService {

    init {
        logger.debug { "Iniciando el servicio de usuarios" }
    }

    override suspend fun findAll(): Flow<User> {
        logger.debug { "findAll: Buscando todos los usuarios" }
        return usuarioRepository.findAll()
    }

    override suspend fun findById(id: UUID): User? {
        logger.debug { "findById: Buscando usuario por id $id" }
        return usuarioRepository.findById(id)
    }

    override suspend fun save(entity: User): User {
        logger.debug { "save: Creando usuario" }
        val existeUsuario = usuarioRepository.findByUsername(entity.username)
        if (existeUsuario != null) {
            println("Ya existe un usuario con el nombre ${entity.username}")
        }

        val user = entity.copy(
            id = UUID.randomUUID(),
            password = entity.password,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return usuarioRepository.save(user)
    }

    override suspend fun update(id: UUID, entity: User): User? {
        logger.debug { "update: Actualizando usuario con id $id" }
        val existeUsuario = usuarioRepository.findByUsername(entity.username)
        if (existeUsuario != null && existeUsuario.id != id) {
            println("Ya existe un usuario con el nombre ${entity.username}")
        }

        val user = entity.copy(
            updatedAt = LocalDateTime.now()
        )

        return usuarioRepository.update(id, user)
    }

    override suspend fun delete(id: UUID): User? {
        logger.debug { "delete: Borrando usuario con id $id" }
        val user = usuarioRepository.findById(id)
        user?.let {
            usuarioRepository.delete(it)
        }
        return user
    }
}