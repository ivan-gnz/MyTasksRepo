import { Component, OnInit, Input } from '@angular/core';

import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

   tasks: Task[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.getTasks();
  }

  getTasks(): void {
    this.taskService.getTasks()
    .subscribe(tasks => this.tasks = tasks);
  }

  add(description: string, status: string): void {
    if (!description) { return; }
    switch (status) {
      case '1': status = 'In Progress'; break;
      case '2': status = 'Pending'; break;
      case '3': status = 'Finished'; break;
    }
    this.taskService.addTask({ description, status } as Task)
      .subscribe(task => {
        this.tasks.push(task);
      });
  }

  save(task: Task): void {
      this.taskService.updateTask(task)
        .subscribe();
  }

  delete(task: Task): void {
    this.tasks = this.tasks.filter(h => h !== task);
    this.taskService.deleteTask(task.id).subscribe();
  }

}
