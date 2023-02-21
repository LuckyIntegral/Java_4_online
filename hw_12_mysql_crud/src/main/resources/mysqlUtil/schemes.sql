create table students
(
    id bigint auto_increment primary key,
    first_name varchar(255) null,
    last_name varchar(255) null,
    age int,
    created_time timestamp null
);

create table courses
(
    id bigint auto_increment primary key,
    course_name varchar(255) null,
    course_subject varchar(255) null,
    created_time timestamp null
);

create table stud_curses_deps
(
    stud_id bigint not null,
    course_id bigint not null,
    primary key (stud_id, course_id),
    foreign key (stud_id) references students(id),
    foreign key (course_id) references courses(id)
);