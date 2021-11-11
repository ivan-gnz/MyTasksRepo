import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Task } from './task';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const tasks = [
      { id: 1, status: 'In progress', description: 'Backend, microservicios con Spring Boot.' },
      { id: 2, status: 'Pending', description: 'Repositorio proyecto, crear repositorio publico de GitHub.' },
      { id: 3, status: 'Finished', description: 'DevOps, desplegar imagen de la aplicacion desde DockerHub en AKS.' },
      { id: 4, status: 'Pending', description: 'CRUD, contrato CRUD con Swagger editor' },
      { id: 5, status: 'In progress', description: 'Frontend, con angular (typescript)' }
    ];
    return {tasks};
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(tasks: Task[]): number {
    return tasks.length > 0 ? Math.max(...tasks.map(task => task.id)) + 1 : 1;
  }
}