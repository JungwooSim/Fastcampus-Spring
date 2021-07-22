## Spring Batch를 활용한 데이터 일괄 처리 
### Batch

**특징**

- 일정 기간으로 스케줄링하여 처리
- 유저와 상호 작용 없이 동작
- 대용량 데이터 처리

**고려 사항**

- 최대한 단순화
- 메모리에 무리 가지 않는 수준으로 많이 처리
- 데이터 무결성 고려
- DB 쿼리 최적화

**사용 사례**

- 오래된 데이터 삭제
- 통계 데이터 집계
- 읽기와 쓰기의 분리
- 데이터 상태 변경
- 데이터 마이그레이션
- 모니터링

### Spring Batch?

- Logging / Tracing, Transaction Management, Job Processing Statistics, Resource Management 등 대량의 레코드 처리에 필수적인 재사용 가능한 기능을 제공
- 최적화 및 파티셔닝 기술을 통해 대용량 및 고성능 배치 작업을 가능하게 하는 고급 기술 서비스 및 기능을 제공
- Spring Framework 를 사용
- Run, Job, Application 구분 → 역할에 따라 구분되기 때문에 유지보수가 용이
- 빌드 툴을 이용하여 빌드된  Application 과 완전히 분리된 아키텍처 JAR 로 간단한 배포 모델 제공

### Domain Language

- Job - 작업, 하나의 Job Flow 에 여러 Step 을 가질 수 있다.
    - JobInstance - 논리적 작업의 실행 단위
    - JobParametres - 배치 작업을 시작하는데 사용하는 파라미터 세트를 가짐
        - Ex. java -jar batch.jar —job.name=jobName
    - JobExecution - 실제 물리적인 실행의 단위

<img src="/batch/img/img-1.png" width="500px;">

- Step - 단계, 모든 Job은 하나 이상의 Step 을 가진다. (설계는 비즈니스 성격에 맞게 진행하면된다. Ex. Job  단위로 개발할지, Step 단위로 개발할지)
    - StepExecution - step 실행 단위, 실제로 시작될 때 생성
    - ExecutionContex

<img src="/batch/img/img-2.png" width="500px;">

<img src="/batch/img/img-3.png" width="500px;">

- JobRepository - Batch 메타 데이터 엔티티의 지속성을 담당하는 저장소
- JobLauncher - JobRepository 로부터 유효한 JobExecution을 획득하고 Job을 실행
- ItemReader
- ItemWriter
- ItemProcessor

### Spring Batch - Schema

- BATCH_JOB_EXECUTION
- BATCH_JOB_EXECUTION_CONTEXT
- BATCH_JOB_EXECUTION_PARAMS
- BATCH_JOB_EXECUTION_SEQ
- BATCH_JOB_INSTANCE
- BATCH_JOB_SEQ
- BATCH_STEP_EXECUTION
- BATCH_STEP_EXECUTION_CONTEXT
- BATCH_STEP_EXECUTION_SEQ

### Tasklet, ChunkOrientedTasklet

- Tasklet
    - 하나의 Step 에서 단일 Task 를 수행
    - Tasklet 인터페이스를 구현
- ChunkOrientedTasklet
    - 하나의 Step 에서 읽기, 처리, 쓰기의 과정으로 Task 를 수행
    - Page Size 단위로 읽고 처리한 데이터가 Chunk Size에 도달하면 쓰고 커밋처리하게 된다.

### Listener

- ItemReadListener : item 읽기에 대한 리스너 인터페이스
- ItemProcessListener : item 처리에 대한 리스너 인터페이스
- ItemWriteListener : item 쓰기에 대한 리스너 인터페이스
- StepExecutionListener : Step 의 생명주기에 대한 리스너 인터페이스
- JobExecutionListener : Job 의 생명주기에 대한 리스너 인터페이스

### Parallel Processing

- Multi-thread Step
  - TaskExecutor 설정
- Parallel Step
  - step 으로 분리하여 병렬처리
- Remote Chunking
- Partioning
  - Step 실행을 분할하고 원격으로 실행하기 위한 SPI 를 제공

### Spring Batch Scheduling

- Linux crontab, Windows Task Scheduler
- Spring Scheduler, Quartz
- Spring Cloud Data Flow
- Jenkins, Airflow
