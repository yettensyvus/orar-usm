package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.mapper.LessonMapper;
import com.yettensyvus.orarUSM.model.*;
import com.yettensyvus.orarUSM.repository.*;
import com.yettensyvus.orarUSM.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final ScheduleRepository scheduleRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final LessonMapper lessonMapper;

    public LessonServiceImpl(LessonRepository lessonRepository,
                           SubjectRepository subjectRepository,
                           TeacherRepository teacherRepository,
                           ClassroomRepository classroomRepository,
                           ScheduleRepository scheduleRepository,
                           TimeSlotRepository timeSlotRepository,
                           LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
        this.scheduleRepository = scheduleRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LessonDto getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        return lessonMapper.toDto(lesson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsByTeacher(Long teacherId) {
        return lessonRepository.findByTeacherId(teacherId)
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsByGroup(Long groupId) {
        return lessonRepository.findByGroupsId(groupId)
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsBySchedule(Long scheduleId) {
        return lessonRepository.findByScheduleId(scheduleId)
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsByClassroom(Long classroomId) {
        return lessonRepository.findByClassroomId(classroomId)
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
        Lesson lesson = lessonMapper.toEntity(lessonDto);
        
        // Set required relationships
        if (lessonDto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(lessonDto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found with id: " + lessonDto.getSubjectId()));
            lesson.setSubject(subject);
        }
        
        if (lessonDto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(lessonDto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + lessonDto.getTeacherId()));
            lesson.setTeacher(teacher);
        }
        
        if (lessonDto.getClassroomId() != null) {
            Classroom classroom = classroomRepository.findById(lessonDto.getClassroomId())
                    .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + lessonDto.getClassroomId()));
            lesson.setClassroom(classroom);
        }
        
        if (lessonDto.getScheduleId() != null) {
            Schedule schedule = scheduleRepository.findById(lessonDto.getScheduleId())
                    .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + lessonDto.getScheduleId()));
            lesson.setSchedule(schedule);
        }
        
        if (lessonDto.getTimeSlotId() != null) {
            TimeSlot timeSlot = timeSlotRepository.findById(lessonDto.getTimeSlotId())
                    .orElseThrow(() -> new RuntimeException("TimeSlot not found with id: " + lessonDto.getTimeSlotId()));
            lesson.setTimeSlot(timeSlot);
        }
        
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toDto(savedLesson);
    }

    @Override
    public LessonDto updateLesson(Long id, LessonDto lessonDto) {
        Lesson existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        
        existingLesson.setWeekType(lessonDto.getWeekType());
        
        // Update relationships if provided
        if (lessonDto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(lessonDto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found with id: " + lessonDto.getSubjectId()));
            existingLesson.setSubject(subject);
        }
        
        if (lessonDto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(lessonDto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + lessonDto.getTeacherId()));
            existingLesson.setTeacher(teacher);
        }
        
        if (lessonDto.getClassroomId() != null) {
            Classroom classroom = classroomRepository.findById(lessonDto.getClassroomId())
                    .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + lessonDto.getClassroomId()));
            existingLesson.setClassroom(classroom);
        }
        
        if (lessonDto.getScheduleId() != null) {
            Schedule schedule = scheduleRepository.findById(lessonDto.getScheduleId())
                    .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + lessonDto.getScheduleId()));
            existingLesson.setSchedule(schedule);
        }
        
        if (lessonDto.getTimeSlotId() != null) {
            TimeSlot timeSlot = timeSlotRepository.findById(lessonDto.getTimeSlotId())
                    .orElseThrow(() -> new RuntimeException("TimeSlot not found with id: " + lessonDto.getTimeSlotId()));
            existingLesson.setTimeSlot(timeSlot);
        }
        
        Lesson updatedLesson = lessonRepository.save(existingLesson);
        return lessonMapper.toDto(updatedLesson);
    }

    @Override
    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new RuntimeException("Lesson not found with id: " + id);
        }
        lessonRepository.deleteById(id);
    }
}
