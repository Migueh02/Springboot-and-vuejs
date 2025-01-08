<template>
  <div>
    <h2>Editar Estudiante</h2>
    <form @submit.prevent="updateEstudiante">
      <div>
        <label for="cc">Cédula:</label>
        <input v-model="estudiante.cc" type="number" id="cc" required />
      </div>
      <div>
        <label for="Nombre">Nombre:</label>
        <input v-model="estudiante.Nombre" type="text" id="Nombre" required />
      </div>
      <div>
        <label for="Apellido">Apellido:</label>
        <input v-model="estudiante.Apellido" type="text" id="Apellido" required />
      </div>
      <div>
        <label for="Telefono">Teléfono:</label>
        <input v-model="estudiante.Telefono" type="text" id="Telefono" required />
      </div>
      <div>
        <label for="Email">Correo:</label>
        <input v-model="estudiante.Email" type="email" id="Email" required />
      </div>
      <div>
        <label for="Carrera">Carrera:</label>
        <input v-model="estudiante.Carrera" type="text" id="Carrera" required />
      </div>
      <button type="submit">Actualizar Estudiante</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      estudiante: {
        id: null,
        cc: "",
        Nombre: "",
        Apellido: "",
        Telefono: "",
        Email: "",
        Carrera: "",
      },
    };
  },
  methods: {
    async getEstudiante(id) {
      try {
        const response = await axios.get(`http://localhost:8080/api/uniminuto/id/${id}`);
        this.estudiante = response.data;
      } catch (error) {
        console.error("Error al obtener el estudiante:", error);
      }
    },
    async updateEstudiante() {
    try {
      await axios.put(`http://localhost:8080/api/uniminuto/estudiantes/${this.estudiante.id}`, this.estudiante);
      alert("Estudiante actualizado correctamente");
      this.$router.push(`/edit-estudiante/${this.estudiante.id}`); // Redirigir a la lista de estudiantes
    } catch (error) {
      console.error("Error al actualizar el estudiante:", error);
    }
  },
  },
  mounted() {
    const estudianteId = this.$route.params.id; // Obtener el id del estudiante desde la URL
    this.getEstudiante(estudianteId);
  },
};
</script>
