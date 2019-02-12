# crud

select p.name as project, t.name as task, t.priority from task t
join project_tasks pt on pt.tasks_id =t.id 
join project p on p.id = pt.project_id
where p.id = 5 and t.finished=0;
