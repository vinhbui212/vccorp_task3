update task 2:
 @Cacheable("studentsCache")
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }

    @Cacheable(value = "studentsCache", key = "#id")
    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).orElse(null);
    }
Lưu giá trị student vào cache để lần sau lấy thì sẽ lấy nhanh trong cache ko cần phải truy cập query trên csdl giúp tăng tốc ứng dụng
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
Khó khăn khi triển khai:  mới hiểu sơ sơ về cách hoạt động của cache 
Ưu điểm: dễ triển khai trên spring boot và dễ config hơn redis
Redis
Nó là hệ thống lưu trữ dữ liệu với dạng KEY-VALUE rất mạnh mẽ và phổ biến hiện nay. Redis nổi bật bởi việc hỗ trợ nhiều cấu trúc dữ liệu cơ bản như:hash, list, set,
sorted set, string… Tất cả dữ liệu được ghi và lưu trên ram, do đó tốc độ đọc ghi dữ liệu rất là nhanh
Khó khăn khi triển khai: chưa cài đặt đc server cho redis


Tìm hiểu về caching
A. Caching là gì?
Caching là một kỹ thuật trong lập trình máy tính được sử dụng để lưu trữ tạm thời các dữ liệu, kết quả của các phép toán, hoặc các tài nguyên có thể được truy cập một cách nhanh chóng. Mục tiêu của caching là giảm thời gian truy cập dữ liệu, tăng cường hiệu suất và giảm gánh nặng cho hệ thống.

Khi một ứng dụng yêu cầu dữ liệu, trước hết nó sẽ kiểm tra xem dữ liệu đã được lưu trong bộ nhớ cache hay chưa. Nếu dữ liệu đã tồn tại trong cache, ứng dụng sẽ lấy nhanh chóng từ đó thay vì phải truy cập vào nơi lữu trữ gốc (ví dụ: cơ sở dữ liệu, API, hoặc tệp tin). Nếu dữ liệu không có trong cache, ứng dụng sẽ lấy từ nơi lưu trữ gốc và sau đó lưu vào cache để sử dụng cho các lần truy cập sau.

Caching thường được sử dụng để giảm độ trễ, tăng tốc độ xử lý, và giảm tải cho nguồn lực hệ thống. Các kỹ thuật caching có thể áp dụng cho nhiều loại dữ liệu và tài nguyên, bao gồm dữ liệu cơ sở dữ liệu, kết quả của phương thức tính toán, tệp tin tĩnh, và nhiều hơn nữa.
B. Ưu điểm của Caching
Để nói về ưu diểm của caching thì chắc kể mỏi mồm, thực tế chứng minh điều này bởi vì nó được sử dụng bởi hầu hết các ứng dụng. Nhưng sau đây là một vài ưu điểm chính của caching mang lại.

1. Tăng hiệu suất
Caching giảm thời gian truy cập dữ liệu từ nơi lưu trữ gốc, giúp ứng dụng hoạt động nhanh chóng hơn từ đó góp phần rất lớn giúp cho ứng dụng của bạn tăng hiệu suất làm việc.

Ví dụ: Một trang web sử dụng caching để lưu trữ tạm thời hình ảnh và tệp CSS, giảm thời gian tải trang cho người dùng.

2. Giảm Gánh Nặng Cho Cơ Sở Dữ Liệu
Caching giảm số lượng truy vấn trực tiếp đến cơ sở dữ liệu, giảm áp lực lên cơ sở dữ liệu và cải thiện hiệu suất hệ thống. Với những ứng dụng nhỏ, dữ liệu không quá nhiều, thì việc truy cập vào DB có thể sẽ không phải là một vấn đề quá lo lắng. Nhưng với những ứng dụng lớn, với hàng trăm, hàng ngàn query vào cơ sở dữ liệu mỗi giây, thì caching là một giải pháp cứu cánh cho đôi vai của ứng dụng. 1s để lấy dữ liệu từ DB so với chỉ vài chục mili giây để lấy từ bộ nhớ cache - cũng xứng đáng đấy chứ.

Ví dụ: Một ứng dụng di động sử dụng caching để lưu trữ kết quả từ API và tránh việc gửi yêu cầu đến máy chủ mỗi lần người dùng mở ứng dụng.

3. Cải Thiện Trải Nghiệm Người Dùng
Caching giúp cung cấp trải nghiệm người dùng mượt mà hơn và giảm độ trễ. Giống như mình đã nói ở trên, việc lấy dữ liệu ở trong cache sẽ nhanh hơn nhiều khi lấy trong cơ sở dữ liệu, chính vì thế kết quả trả về cho người dùng sẽ nhanh hơn, thậm chí không cảm thấy có độ trễ - còn gì tuyệt vời hơn đúng không.

Ví dụ: Trình duyệt web lưu trữ tạm thời các tệp tin hình ảnh và script để tăng tốc độ tải trang web.

4. Cải Thiện Độ Ổn Định Hệ Thống
Caching giúp cải thiện độ ổn định của hệ thống bằng cách giảm độ trễ và gánh nặng, giúp hệ thống chạy mượt mà hơn.

Ví dụ: Một ứng dụng trò chơi trực tuyến sử dụng caching để lưu trữ thông tin về người chơi và giảm độ trễ trong quá trình chơi game.

C. Những lưu ý khi sử dụng Caching
Việc sử dụng Cache và áp dụng nó không phải là một vấn đề khó khăn. Cái khó của caching nằm ở việc bạn quản lý dữ liệu cache như thế để nó hoạt động đúng với những ưu điểm mà ta đã đề cập ở trên. Vì vậy sau đây sẽ là những lưu ý mà bạn cần chú ý khi sử dụng Caching trong dự án của mình.

1. Rủi Ro Dữ Liệu Lỗi Thời (Stale Data)
Nếu dữ liệu trong cache không được cập nhật thường xuyên, có thể dẫn đến sử dụng dữ liệu lỗi thời (stale). Quay lại với ví dụ ở trên, giả sử thời hạn sử dụng của Cà phê muối là 3 ngày là hỏng, nhưng cửa hàng bạn đóng cửa 7 ngày mới mở lại, sau khi mở lại bạn lại tiếp tục dùng số Cà phê muối đã pha sẵn để bán cho khách thì điều gì sẽ xảy ra. Hay một ví dụ khác: Một trang web tin tức không cập nhật cache đúng cách, dẫn đến việc hiển thị tin tức cũ cho người đọc.

Điều này cho thấy rằng bạn cũng cần phải kiểm tra và cập nhật dữ liệu trong cache thường xuyên để tránh cho nó rơi vào tình trạng như trên.

2. Quản Lý Thời Gian Sống (Time-to-Live) Cẩn Thận
Thiết lập thời gian sống (time-to-live) sao cho phù hợp với tính chất của dữ liệu và mức độ thay đổi. Nhưng bạn cũng phải cẩn thận và tính toán kỹ lưỡng cho khoảng thời gian này để tránh dữ liệu lỗi thời hoặc re-fresh quá nhiều khiến việc cache trở nên vô nghĩa. Giống như việc cứ sau 3 ngày thì bạn sẽ phải thay những cốc Cà phê muối pha sẵn còn trên kệ bằng những cốc mới vậy.

Ví dụ: Một trang web tin tức có thể cập nhật cache cho các bài viết nổi bật mỗi giờ để đảm bảo

3. Quản Lý Bộ Nhớ Hiệu Quả
Caching có thể làm tăng tiêu tốn bộ nhớ, đặc biệt là khi lưu trữ lớn lượng dữ liệu. Chính vì thế, kiểm soát lượng bộ nhớ sử dụng cho caching để tránh tình trạng chiếm dụng bộ nhớ quá mức. Ví dụ: Một ứng dụng di động lưu trữ nhiều hình ảnh chất lượng cao trong bộ nhớ để cải thiện trải nghiệm, nhưng có thể gặp vấn đề với các thiết bị có bộ nhớ thấp.

4. Xác Định Rõ Mục Tiêu và Áp dụng chiến lược hợp lý
Đặt rõ mục tiêu bạn muốn đạt được với caching, liệu bạn đang tập trung vào tăng tốc độ, giảm áp lực cho cơ sở dữ liệu hay cải thiện trải nghiệm người dùng từ đó lựa chọn chiến lược caching phù hợp với yêu cầu và tính chất của ứng dụng.
