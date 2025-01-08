<template>
  <div id="app">
    <h1>CRUD de Estudiantes</h1>
    <Estudiantes />
  </div>
</template>

<script>
import Estudiantes from './components/EstudiantesList.vue'

export default {
  name: 'App',
  components: {
    Estudiantes
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
