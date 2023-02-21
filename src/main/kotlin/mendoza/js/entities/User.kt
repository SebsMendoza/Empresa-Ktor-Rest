package mendoza.js.entities

import org.ufoss.kotysa.h2.H2Table
import java.time.LocalDateTime
import java.util.UUID

object UsuariosTable : H2Table<UsuarioEntity>("usuarios") {
    val id = uuid(UsuarioEntity::id).primaryKey()
    val nombre = varchar(UsuarioEntity::nombre, size = 100)
    val email = varchar(UsuarioEntity::email, size = 100)
    val username = varchar(UsuarioEntity::username, size = 50)
    val password = varchar(UsuarioEntity::password, size = 100)
    val avatar = varchar(UsuarioEntity::avatar, size = 100)
    val role = varchar(UsuarioEntity::role, size = 100)
    val createdAt = timestamp(UsuarioEntity::createdAt, "created_at")
    val updatedAt = timestamp(UsuarioEntity::updatedAt, "updated_at")
    val deleted = boolean(UsuarioEntity::deleted)
}

data class UsuarioEntity(
    val id: UUID,
    val nombre: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String,
    val role: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false
)