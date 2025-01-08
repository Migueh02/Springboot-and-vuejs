import { createRouter, createWebHistory } from 'vue-router'; // Cambia la importación
import EstudiantesList from './components/EstudiantesList.vue'; // Tu componente de lista de estudiantes
import EstudiantesEditar from './components/EstudiantesEditar.vue';

// Crea el router usando Vue 3
const router = createRouter({
  history: createWebHistory(), // Usa createWebHistory para el manejo de rutas
  routes: [
    {
      path: '/estudiantes',
      name: 'estudiantes',
      component: EstudiantesList, // Componente que muestra la lista de estudiantes
    },
    {
      path: '/edit-estudiante/:id', // :id es un parámetro que se usará para identificar el estudiante
      name: 'editEstudiante',
      component: EstudiantesEditar, // Componente para editar estudiante
    },
    {
      path: '*',
      redirect: '/estudiantes', // Redirige a la lista de estudiantes si la ruta no existe
    },
  ],
});

export default router;
