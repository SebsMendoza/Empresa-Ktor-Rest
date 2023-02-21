package mendoza.js.mappers

import mendoza.js.dto.UserCreateDto
import mendoza.js.dto.UserDto
import mendoza.js.entities.UserEntity
import mendoza.js.models.User

fun User.toDto(): UserDto {
    return UserDto(
        id = this.id,
        nombre = this.nombre,
        email = this.email,
        username = this.username,
        avatar = this.avatar,
        role = this.role,
        metadata = UserDto.MetaData(
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            deleted = this.deleted
        )
    )
}

fun UserCreateDto.toModel(): User {
    return User(
        nombre = this.nombre,
        email = this.email,
        username = this.username,
        password = this.password,
        avatar = this.avatar ?: "https://upload.wikimedia.org/wikipedia/commons/f/f4/User_Avatar_2.png",
        role = this.role ?: User.Role.USER
    )
}

fun UserEntity.toModel(): User {
    return User(
        id = this.id,
        nombre = this.nombre,
        email = this.email,
        username = this.username,
        password = this.password,
        avatar = this.avatar,
        role = User.Role.valueOf(this.role),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deleted = this.deleted
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        nombre = this.nombre,
        email = this.email,
        username = this.username,
        password = this.password,
        avatar = this.avatar,
        role = this.role.name,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deleted = this.deleted
    )
}