update task 2:
 @Cacheable("studentsCache")
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }

    @Cacheable(value = "studentsCache", key = "#id")
    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

File pom.xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>org.ehcache</groupId>
			<artifactId>ehcache</artifactId>
			<version>3.9.6</version>
		</dependency>
Báo cáo về cache
Solution: Dùng ehcache và redis

Ehcache
Một thư viện caching có thể được sử dụng độc lập hoặc như một phần của JPA/Hibernate, cung cấp cả in-memory và disk-based caching.
Khởi tạo theo cặp key-value lưu thông tin cần lấy
Khó khăn khi triển khai: chưa chạy đc chương trình mới hiểu sơ sơ về cách hoạt động của cache và chưa biết cách để triển khai cache
Ưu điểm: dễ triển khai trên spring boot và dễ config hơn redis
Redis
Nó là hệ thống lưu trữ dữ liệu với dạng KEY-VALUE rất mạnh mẽ và phổ biến hiện nay. Redis nổi bật bởi việc hỗ trợ nhiều cấu trúc dữ liệu cơ bản như:hash, list, set,
sorted set, string… Tất cả dữ liệu được ghi và lưu trên ram, do đó tốc độ đọc ghi dữ liệu rất là nhanh
Khó khăn khi triển khai: chưa cài đặt đc server cho redis
