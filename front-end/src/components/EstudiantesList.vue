
<style src="../assets/css/css.css"></style>

<template>
  <div>
    <!-- Formulario para Crear Estudiante -->
    <h3 v-if="!editMode">Crear Estudiante</h3>
    <h3 v-if="editMode">Editar Estudiante</h3>
    <form @submit.prevent="editMode ? updateEstudiante() : createEstudiante">
      <input v-model="nuevoEstudiante.cc" placeholder="Cédula" required />
      <input v-model="nuevoEstudiante.nombre" placeholder="Nombre" required />
      <input v-model="nuevoEstudiante.apellido" placeholder="Apellido" required />
      <input v-model="nuevoEstudiante.telefono" placeholder="Teléfono" />
      <input v-model="nuevoEstudiante.email" placeholder="Email" />
      <input v-model="nuevoEstudiante.carrera" placeholder="Carrera" />
      <button type="submit">{{ editMode ? 'Actualizar Estudiante' : 'Crear Estudiante' }}</button>
    </form>

    <!-- Listado de Estudiantes -->
    <h2>Listado de Estudiantes</h2>
    <table>
      <thead>
        <tr>
          <th>CC</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Telefono</th>
          <th>Email</th>
          <th>Carrera</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="estudiante in estudiantes" :key="estudiante.id">
          <td>{{ estudiante.cc }}</td>
          <td>{{ estudiante.nombre }}</td>
          <td>{{ estudiante.apellido }}</td>
          <td>{{ estudiante.telefono }}</td>
          <td>{{ estudiante.email }}</td>
          <td>{{ estudiante.carrera }}</td>
          <td>
            <button @click="editEstudiante(estudiante)">Editar</button>
            <button @click="confirmDelete(estudiante)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Modal de Confirmación de Eliminación -->
    <div v-if="showDeleteModal" class="modal">
      <div class="modal-content">
        <h4>¿Estás seguro de que deseas eliminar este estudiante?</h4>
        <p>{{ estudianteAEliminar ? estudianteAEliminar.nombre : '' }}</p>
        <button @click="deleteEstudiante(estudianteAEliminar.id)">Confirmar</button>
        <button @click="cancelDelete">Cancelar</button>
      </div>
    </div>
  </div>
</template>

  
<script>
import axios from 'axios'

export default {
  data() {
    return {
      estudiantes: [],
      nuevoEstudiante: {
        cc: '',
        Nombre: '',
        Apellido: '',
        Telefono: '',
        Email: '',
        Carrera: ''
      },
      editMode: false, // Controla si estamos editando
      currentEstudiante: null, // Guarda el estudiante actual a editar
      showDeleteModal: false, // Controla la visibilidad del modal de eliminación
      estudianteAEliminar: null, // Guarda el estudiante que se desea eliminar
    }
  },
  methods: {
    // Obtener todos los estudiantes
    async getEstudiantes() {
      try {
        const response = await axios.get('http://localhost:8080/api/uniminuto/estudiantes')
        this.estudiantes = response.data
      } catch (error) {
        console.error('Hubo un error al obtener los estudiantes:', error)
      }
    },
    // Crear un nuevo estudiante
    async createEstudiante() {
      try {
        await axios.post('http://localhost:8080/api/uniminuto/estudiantes', this.nuevoEstudiante)
        this.getEstudiantes()
        this.resetForm()
      } catch (error) {
        console.error('Hubo un error al crear el estudiante:', error)
      }
    },
    // Eliminar un estudiante
    async deleteEstudiante(id) {
      try {
        await axios.delete(`http://localhost:8080/api/uniminuto/estudiantes/${id}`)
        this.getEstudiantes()
        this.cancelDelete() // Cerrar el modal después de la eliminación
      } catch (error) {
        console.error('Hubo un error al eliminar el estudiante:', error)
      }
    },
    // Confirmar la eliminación
    confirmDelete(estudiante) {
      this.estudianteAEliminar = estudiante
      this.showDeleteModal = true // Mostrar el modal de confirmación
    },
    // Cancelar la eliminación y cerrar el modal
    cancelDelete() {
      this.showDeleteModal = false
      this.estudianteAEliminar = null // Limpiar el estudiante seleccionado para eliminar
    },
    // Editar un estudiante
    editEstudiante(estudiante) {
      this.editMode = true // Activamos el modo de edición
      this.currentEstudiante = estudiante // Guardamos el estudiante a editar
      // Cargamos los datos en el formulario
      this.nuevoEstudiante = { ...estudiante }
    },
    // Actualizar un estudiante
    async updateEstudiante() {
      try {
        await axios.put(`http://localhost:8080/api/uniminuto/estudiantes/${this.currentEstudiante.id}`, this.nuevoEstudiante)
        this.getEstudiantes()
        this.resetForm()
      } catch (error) {
        console.error('Hubo un error al actualizar el estudiante:', error)
      }
    },
    // Restablecer el formulario
    resetForm() {
      this.nuevoEstudiante = {
        cc: '',
        Nombre: '',
        Apellido: '',
        Telefono: '',
        Email: '',
        Carrera: ''
      }
      this.editMode = false // Desactivamos el modo de edición
      this.currentEstudiante = null
    }
  },
  mounted() {
    this.getEstudiantes()
  }
}
</script>
  