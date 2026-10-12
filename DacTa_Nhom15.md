# ĐẶC TẢ DỰ ÁN NHÓM - HỆ THỐNG QUẢN LÝ CỬA HÀNG QUẦN ÁO

**Nhóm: 15**  
**Thành viên:** 
| STT | Họ và Tên | MSSV |
|-----|----------|-------|
| 1 | Nguyễn Thị Huyền Trân | 25110374|
| 2 | Nguyễn Ngọc Hồng Nhung| 25110285 |
| 3 | Lê Thị Minh Thư| 25110357 |


## Câu 1. Phát biểu vấn đề

## 1.1. Nội dung về dự án nhóm (MIS)
### 1.1.1. Bối cảnh
Cửa hàng quần áo "3CE" kinh doanh quần áo với nhiều kích cỡ và màu sắc. Hiện cửa hàng quản lý bằng sổ sách và Excel nên gặp khó khăn:

- Không kiểm soát chính xác tồn kho theo từng size/màu.
- Khó tra cứu lịch sử mua hàng, điểm tích lũy của khách.
- Nhập hàng từ nhà cung cấp không được ghi nhận có hệ thống.
- Thống kê doanh thu theo ngày/tháng, nhân viên, sản phẩm mất thời gian, dễ sai.

### 1.1.2. Mục tiêu
Xây dựng hệ thống thông tin quản lý (MIS) nhằm:

- Quản lý danh mục, sản phẩm, biến thể (size, màu) và tồn kho.
- Quản lý khách hàng, nhân viên, nhà cung cấp.
- Quản lý hóa đơn bán hàng và phiếu nhập hàng.
- Áp dụng khuyến mãi khi bán.
- Thống kê doanh thu, hàng bán chạy, hàng tồn.

### 1.1.3. Phạm vi chức năng
1. Quản lý danh mục và sản phẩm.
2. Quản lý biến thể sản phẩm và tồn kho.
3. Quản lý khách hàng và điểm tích lũy.
4. Quản lý nhân viên.
5. Quản lý nhà cung cấp và phiếu nhập.
6. Lập hóa đơn, áp dụng khuyến mãi.
7. Thống kê báo cáo.

## 1.2. Các thực thể

| STT | Thực thể | Mô tả |
|-----|----------|-------|
| 1 | DanhMuc | Loại sản phẩm: áo thun, quần jean, áo len, ...|
| 2 | SanPham | Mẫu sản phẩm chung (tên, thương hiệu, giá) |
| 3 | ChiTietSanPham | Sản phẩm cụ thể theo size + màu, có tồn kho riêng |
| 4 | KhachHang | Khách mua hàng, số điện thoại |
| 5 | NhanVien | Nhân viên bán hàng, quản lý, thủ kho |
| 6 | NhaCungCap | Đơn vị cung cấp hàng |
| 7 | HoaDon | Hóa đơn bán hàng |
| 8 | ChiTietHoaDon | Từng dòng sản phẩm trong hóa đơn |
| 9 | PhieuNhap | Phiếu nhập hàng từ nhà cung cấp |
| 10 | ChiTietPhieuNhap | Từng dòng sản phẩm trong phiếu nhập |
| 11 | KhuyenMai | Chương trình giảm giá |

## 1.3. Thuộc tính của thực thể

### 1.3.1. DanhMuc

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maDanhMuc | int | PK | Mã danh mục |
| tenDanhMuc | String | Mô tả | Tên danh mục |
| moTa | String | Mô tả | Mô tả thêm về danh mục |

### 1.3.2. SanPham

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maSanPham | int | PK | Mã sản phẩm |
| tenSanPham | String | Mô tả | Tên sản phẩm |
| thuongHieu | String | Mô tả | Thương hiệu của sản phẩm |
| gioiTinh | String | Mô tả | Đối tượng sử dụng: nam, nữ hoặc unisex |
| giaBan | double | Mô tả | Giá bán của sản phẩm |
| maDanhMuc | int | FK | Mã danh mục mà sản phẩm thuộc về |
### 1.3.3. BienTheSanPham
| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maBienThe | int | PK | Mã biến thể sản phẩm |
| maSanPham | int | FK | Mã sản phẩm của biến thể |
| kichCo | String | Mô tả | Kích cỡ của sản phẩm |
| mauSac | String | Mô tả | Màu sắc của sản phẩm |
| soLuongTon | int | Mô tả | Số lượng sản phẩm hiện có trong kho |
| maVach | String | Mô tả | Mã vạch của biến thể sản phẩm |

### 1.3.4. KhachHang

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maKhachHang | int | PK | Mã khách hàng |
| hoTen | String | Mô tả | Họ và tên khách hàng |
| soDienThoai | String | Mô tả | Số điện thoại khách hàng |
| email | String | Mô tả | Địa chỉ email của khách hàng |
| ngaySinh | date | Mô tả | Ngày sinh của khách hàng |
| diemTichLuy | int | Mô tả | Số điểm tích lũy của khách hàng |
| hangThanhVien | String | Mô tả | Hạng thành viên của khách hàng |

### 1.3.5. NhanVien

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maNhanVien | int | PK | Mã nhân viên |
| hoTen | String | Mô tả | Họ và tên nhân viên |
| soDienThoai | String | Mô tả | Số điện thoại nhân viên |
| chucVu | String | Mô tả | Chức vụ của nhân viên |
| luongCoBan | double | Mô tả | Mức lương cơ bản của nhân viên |
| ngayVaoLam | date | Mô tả | Ngày nhân viên bắt đầu làm việc |

### 1.3.6. NhaCungCap

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maNhaCungCap | int | PK | Mã nhà cung cấp |
| tenNhaCungCap | String | Mô tả | Tên nhà cung cấp |
| soDienThoai | String | Mô tả | Số điện thoại của nhà cung cấp |
| diaChi | String | Mô tả | Địa chỉ của nhà cung cấp |

### 1.3.7. HoaDon

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maHoaDon | int | PK | Mã hóa đơn |
| ngayLap | datetime | Mô tả | Ngày và thời gian lập hóa đơn |
| maKhachHang | int | FK | Mã khách hàng mua hàng |
| maNhanVien | int | FK | Mã nhân viên lập hóa đơn |
| maKhuyenMai | int | FK | Mã khuyến mãi được áp dụng |
| tongTien | double | Mô tả | Tổng tiền hàng trước khi giảm |
| tienGiam | double | Mô tả | Số tiền được giảm |
| thanhTien | double | Mô tả | Số tiền khách hàng cần thanh toán sau khi giảm |
| phuongThucThanhToan | String | Mô tả | Phương thức thanh toán |
| trangThai | String | Mô tả | Trạng thái của hóa đơn |

### 1.3.8. ChiTietHoaDon

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maHoaDon | int | PK, FK | Mã hóa đơn |
| maBienThe | int | PK, FK | Mã biến thể sản phẩm |
| soLuong | int | Mô tả | Số lượng biến thể được bán |
| donGia | double | Mô tả | Đơn giá của biến thể tại thời điểm bán |

### 1.3.9. KhuyenMai

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maKhuyenMai | int | PK | Mã khuyến mãi |
| tenKhuyenMai | String | Mô tả | Tên chương trình khuyến mãi |
| loaiGiam | String | Mô tả | Loại giảm giá: phần trăm hoặc số tiền |
| giaTriGiam | double | Mô tả | Giá trị giảm |
| ngayBatDau | date | Mô tả | Ngày bắt đầu khuyến mãi |
| ngayKetThuc | date | Mô tả | Ngày kết thúc khuyến mãi |
| donToiThieu | double | Mô tả | Giá trị đơn hàng tối thiểu để áp dụng khuyến mãi |

### 1.3.10. PhieuNhap

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maPhieuNhap | int | PK | Mã phiếu nhập |
| ngayNhap | date | Mô tả | Ngày nhập hàng |
| maNhaCungCap | int | FK | Mã nhà cung cấp |
| maNhanVien | int | FK | Mã nhân viên lập phiếu nhập |
| tongTien | double | Mô tả | Tổng tiền của phiếu nhập |

### 1.3.11. ChiTietPhieuNhap

| Thuộc tính | Kiểu | Loại | Mô tả |
|---|---|---|---|
| maPhieuNhap | int | PK, FK | Mã phiếu nhập |
| maBienThe | int | PK, FK | Mã biến thể sản phẩm |
| soLuong | int | Mô tả | Số lượng biến thể được nhập |
| giaNhap | double | Mô tả | Giá nhập của biến thể |

## 1.4. Mối liên kết

### Các mối liên kết giữa các thực thể

| STT | Liên kết | Bản số | Diễn giải |
|---:|---|:---:|---|
| 1 | DanhMuc – SanPham | 1 – N | Một danh mục có nhiều sản phẩm; mỗi sản phẩm thuộc 1 danh mục |
| 2 | SanPham – BienTheSanPham | 1 – N | Một sản phẩm có nhiều biến thể về kích thước/màu sắc; mỗi biến thể thuộc 1 sản phẩm |
| 3 | KhachHang – HoaDon | 1 – N | Một khách hàng có thể có nhiều hóa đơn; mỗi hóa đơn thuộc tối đa 1 khách hàng |
| 4 | NhanVien – HoaDon | 1 – N | Một nhân viên có thể lập nhiều hóa đơn; mỗi hóa đơn do 1 nhân viên lập |
| 5 | KhuyenMai – HoaDon | 1 – N | Một khuyến mãi có thể áp dụng cho nhiều hóa đơn; mỗi hóa đơn áp dụng tối đa 1 khuyến mãi |
| 6 | HoaDon – ChiTietHoaDon | 1 – N | Một hóa đơn có nhiều chi tiết hóa đơn; mỗi chi tiết hóa đơn thuộc 1 hóa đơn |
| 7 | BienTheSanPham – ChiTietHoaDon | 1 – N | Một biến thể sản phẩm có thể xuất hiện trong nhiều chi tiết hóa đơn; mỗi chi tiết hóa đơn tham chiếu 1 biến thể sản phẩm |
| 8 | NhaCungCap – PhieuNhap | 1 – N | Một nhà cung cấp có thể cung cấp nhiều phiếu nhập; mỗi phiếu nhập thuộc 1 nhà cung cấp |
| 9 | NhanVien – PhieuNhap | 1 – N | Một nhân viên có thể lập nhiều phiếu nhập; mỗi phiếu nhập do 1 nhân viên lập |
| 10 | PhieuNhap – ChiTietPhieuNhap | 1 – N | Một phiếu nhập có nhiều chi tiết phiếu nhập; mỗi chi tiết phiếu nhập thuộc 1 phiếu nhập |
| 11 | BienTheSanPham – ChiTietPhieuNhap | 1 – N | Một biến thể sản phẩm có thể xuất hiện trong nhiều chi tiết phiếu nhập; mỗi chi tiết phiếu nhập tham chiếu 1 biến thể sản phẩm |

## 1.5. Sơ đồ ERD

![SƠ ĐỒ ERD](https://i.postimg.cc/9FT5B784/erd.png)

## Câu 2 . Chuyển các quan hệ ERs/RRs thành các lớp trong project Java chạy trên IntelliJ

## 2.1. Lớp `DanhMuc`

```java
class DanhMuc {
    private int maDanhMuc;
    private String tenDanhMuc;
    private String moTa;

    private List<SanPham> danhSachSanPham;
}
```

## 2.2. Lớp `SanPham`

```java
class SanPham {
    private int maSanPham;
    private String tenSanPham;
    private String thuongHieu;
    private String gioiTinh;
    private double giaBan;

    private DanhMuc danhMuc;
    private List<BienTheSanPham> danhSachBienThe;
}
```
## 2.3. Lớp `BienTheSanPham`

```java
class BienTheSanPham {
    private int maBienThe;
    private String kichCo;
    private String mauSac;
    private int soLuongTon;
    private String maVach;

    private SanPham sanPham;
    private List<ChiTietHoaDon> danhSachChiTietHoaDon;
    private List<ChiTietPhieuNhap> danhSachChiTietPhieuNhap;
}
```

## 2.4. Lớp `KhachHang`

```java
class KhachHang {
    private int maKhachHang;
    private String hoTen;
    private String soDienThoai;
    private String email;
    private LocalDate ngaySinh;
    private int diemTichLuy;
    private String hangThanhVien;

    private List<HoaDon> danhSachHoaDon;
}
```

## 2.5. Lớp `NhanVien`

```java
class NhanVien {
    private int maNhanVien;
    private String hoTen;
    private String soDienThoai;
    private String chucVu;
    private double luongCoBan;
    private LocalDate ngayVaoLam;

    private List<HoaDon> danhSachHoaDon;
    private List<PhieuNhap> danhSachPhieuNhap;
}
```

## 2.6. Lớp `NhaCungCap`

```java
class NhaCungCap {
    private int maNhaCungCap;
    private String tenNhaCungCap;
    private String soDienThoai;
    private String diaChi;

    private List<PhieuNhap> danhSachPhieuNhap;
}
```

## 2.7. Lớp `HoaDon`

```java
class HoaDon {
    private int maHoaDon;
    private LocalDateTime ngayLap;
    private int maKhachHang;
    private int maNhanVien;
    private int maKhuyenMai;
    private double tongTien;
    private double tienGiam;
    private double thanhTien;
    private String phuongThucThanhToan;
    private String trangThai;

    private KhachHang khachHang;
    private NhanVien nhanVien;
    private KhuyenMai khuyenMai;
    private List<ChiTietHoaDon> danhSachChiTiet;
}
```

## 2.8. Lớp `ChiTietHoaDon`

```java
class ChiTietHoaDon {
    private int maHoaDon;
    private int maBienThe;
    private int soLuong;
    private double donGia;

    private HoaDon hoaDon;
    private BienTheSanPham bienTheSanPham;
}
```

## 2.9. Lớp `KhuyenMai`

```java
class KhuyenMai {
    private int maKhuyenMai;
    private String tenKhuyenMai;
    private String loaiGiam;
    private double giaTriGiam;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private double donToiThieu;

    private List<HoaDon> danhSachHoaDon;
}
```

## 2.10. Lớp `PhieuNhap`

```java
class PhieuNhap {
    private int maPhieuNhap;
    private LocalDate ngayNhap;
    private int maNhaCungCap;
    private int maNhanVien;
    private double tongTien;

    private NhaCungCap nhaCungCap;
    private NhanVien nhanVien;
    private List<ChiTietPhieuNhap> danhSachChiTiet;
}
```

## 2.11. Lớp `ChiTietPhieuNhap`

```java
class ChiTietPhieuNhap {
    private int maPhieuNhap;
    private int maBienThe;
    private int soLuong;
    private double giaNhap;

    private PhieuNhap phieuNhap;
    private BienTheSanPham bienTheSanPham;
}
```
## Câu 3 . Tạo dữ liệu cho các thực thể ERs/RRs, các enum

## 3.1. Các enum sử dụng trong dự án

Để hạn chế việc nhập dữ liệu không hợp lệ và giúp các thuộc tính có tập giá trị rõ ràng, dự án sử dụng một số enum cho các thuộc tính có giá trị cố định.

| Enum | Giá trị |
|---|---|
| GioiTinh | NAM, NU, UNISEX, TRE_EM |
| KichCo | XS, S, M, L, XL, XXL |
| HangThanhVien | THUONG, BAC, VANG, KIM_CUONG |
| ChucVu | QUAN_LY, BAN_HANG, THU_KHO |
| PhuongThucThanhToan | TIEN_MAT, CHUYEN_KHOAN, THE |
| TrangThaiHoaDon | DA_THANH_TOAN, DA_HUY, DOI_TRA |
| LoaiGiam | PHAN_TRAM, SO_TIEN |

## 3.2. Dữ liệu mẫu cho các thực thể

Dữ liệu được xây dựng nhằm mô phỏng hoạt động của cửa hàng quần áo, bao gồm danh mục, sản phẩm, biến thể sản phẩm, khách hàng, nhân viên, nhà cung cấp, hóa đơn, khuyến mãi và phiếu nhập.

### 3.2.1. Dữ liệu `DanhMuc`

| maDanhMuc | tenDanhMuc | moTa |
|---|---|---|
| DM001 | Áo | Các loại áo thời trang |
| DM002 | Quần | Các loại quần thời trang |
| DM003 | Váy | Các loại váy thời trang |

---

### 3.2.2. Dữ liệu `SanPham`

| maSanPham | tenSanPham | thuongHieu | gioiTinh | giaBan | maDanhMuc |
|---|---|---|---|---:|---|
| SP001 | Áo thun Basic | Uniqlo | UNISEX | 199000 | DM001 |
| SP002 | Áo sơ mi Oxford | Routine | NAM | 399000 | DM001 |
| SP003 | Quần Jean Slim | Levi's | NAM | 799000 | DM002 |
| SP004 | Quần ống rộng | H&M | NU | 599000 | DM002 |
| SP005 | Váy hoa mùa hè | Zara | NU | 699000 | DM003 |

---

### 3.2.3. Dữ liệu `BienTheSanPham`

| maBienThe | maSanPham | kichCo | mauSac | soLuongTon | maVach |
|---|---|---|---|---:|---|
| BT001 | SP001 | M | Trắng | 20 | 893000000001 |
| BT002 | SP001 | L | Đen | 15 | 893000000002 |
| BT003 | SP002 | M | Xanh | 10 | 893000000003 |
| BT004 | SP003 | 30 | Xanh đậm | 12 | 893000000004 |
| BT005 | SP004 | M | Đen | 18 | 893000000005 |
| BT006 | SP005 | S | Hoa xanh | 8 | 893000000006 |

---

### 3.2.4. Dữ liệu `KhachHang`

| maKhachHang | hoTen | soDienThoai | email | ngaySinh | diemTichLuy | hangThanhVien |
|---|---|---|---|---|---:|---|
| KH001 | Nguyễn Minh Anh | 0901234567 | minhanh@gmail.com | 2005-03-15 | 120 | THUONG |
| KH002 | Trần Ngọc Mai | 0912345678 | ngocmai@gmail.com | 2004-07-21 | 580 | BAC |
| KH003 | Lê Hoàng Nam | 0923456789 | hoangnam@gmail.com | 2003-11-02 | 1250 | VANG |
| KH004 | Phạm Thu Hà | 0934567890 | thuha@gmail.com | 2005-01-18 | 2500 | KIM_CUONG |

---

### 3.2.5. Dữ liệu `NhanVien`
| maNhanVien | hoTen | soDienThoai | chucVu | luongCoBan | ngayVaoLam |
|---|---|---|---|---:|---|
| NV001 | Nguyễn Văn An | 0901111111 | Nhân viên bán hàng | 7000000 | 2025-01-10 |
| NV002 | Trần Thị Lan | 0902222222 | Nhân viên kho | 7500000 | 2025-03-15 |
| NV003 | Lê Minh Tuấn | 0903333333 | Quản lý cửa hàng | 12000000 | 2024-08-01 |

---

### 3.2.6. Dữ liệu `NhaCungCap`

| maNhaCungCap | tenNhaCungCap | soDienThoai | diaChi |
|---|---|---|---|
| NCC001 | Công ty May Việt Tiến | 0281111111 | TP. Hồ Chí Minh |
| NCC002 | Công ty Dệt May Thành Công | 0282222222 | TP. Hồ Chí Minh |
| NCC003 | Công ty Thời Trang ABC | 0283333333 | Bình Dương |

---

### 3.2.7. Dữ liệu `KhuyenMai`

| maKhuyenMai | tenKhuyenMai | loaiGiam | giaTriGiam | ngayBatDau | ngayKetThuc | donToiThieu |
|---|---|---|---:|---|---|---:|
| KM001 | Giảm 10% mùa hè | PHAN_TRAM | 10 | 2026-06-01 | 2026-06-30 | 500000 |
| KM002 | Giảm 50000 đồng | SO_TIEN | 50000 | 2026-07-01 | 2026-07-31 | 700000 |
| KM003 | Khuyến mãi cuối tuần | PHAN_TRAM | 15 | 2026-08-01 | 2026-08-31 | 1000000 |

---

### 3.2.8. Dữ liệu `HoaDon`

| maHoaDon | ngayLap | maKhachHang | maNhanVien | maKhuyenMai | tongTien | tienGiam | thanhTien | phuongThucThanhToan | trangThai |
|---|---|---|---|---|---:|---:|---:|---|---|
| HD001 | 2026-08-05 09:30 | KH001 | NV001 | KM001 | 798000 | 79800 | 718200 | TIEN_MAT | DA_THANH_TOAN |
| HD002 | 2026-08-06 14:20 | KH002 | NV001 | KM002 | 1198000 | 50000 | 1148000 | CHUYEN_KHOAN | DA_THANH_TOAN |
| HD003 | 2026-08-07 16:45 | KH003 | NV003 | KM003 | 1598000 | 239700 | 1358300 | THE | DA_THANH_TOAN |
| HD004 | 2026-08-08 10:15 | KH004 | NV001 | KM001 | 699000 | 0 | 699000 | CHUYEN_KHOAN | DA_THANH_TOAN |

---

### 3.2.9. Dữ liệu `ChiTietHoaDon`

| maHoaDon | maBienThe | soLuong | donGia |
|---|---|---:|---:|
| HD001 | BT001 | 2 | 199000 |
| HD001 | BT003 | 1 | 399000 |
| HD002 | BT004 | 1 | 799000 |
| HD002 | BT005 | 1 | 599000 |
| HD003 | BT004 | 2 | 799000 |
| HD004 | BT006 | 1 | 699000 |

> `ChiTietHoaDon` sử dụng cặp `maHoaDon` và `maBienThe` để xác định dòng sản phẩm trong hóa đơn.

---

### 3.2.10. Dữ liệu `PhieuNhap`

| maPhieuNhap | ngayNhap | maNhaCungCap | maNhanVien | tongTien |
|---|---|---|---|---:|
| PN001 | 2026-07-20 | NCC001 | NV002 | 5000000 |
| PN002 | 2026-07-25 | NCC002 | NV002 | 7500000 |
| PN003 | 2026-08-01 | NCC003 | NV003 | 6000000 |

---

### 3.2.11. Dữ liệu `ChiTietPhieuNhap`

| maPhieuNhap | maBienThe | soLuong | giaNhap |
|---|---|---:|---:|
| PN001 | BT001 | 30 | 120000 |
| PN001 | BT002 | 25 | 120000 |
| PN002 | BT003 | 20 | 250000 |
| PN002 | BT004 | 15 | 550000 |
| PN003 | BT005 | 20 | 400000 |
| PN003 | BT006 | 15 | 480000 |

---

## 3.3. Kiểm tra tính liên kết của dữ liệu

Các dữ liệu mẫu được xây dựng bảo đảm các khóa liên kết giữa các thực thể tồn tại trong dữ liệu:

| Quan hệ | Kiểm tra |
|---|---|
| `DanhMuc → SanPham` | `maDanhMuc` trong `SanPham` phải tồn tại trong `DanhMuc` |
| `SanPham → BienTheSanPham` | `maSanPham` trong `BienTheSanPham` phải tồn tại trong `SanPham` |
| `KhachHang → HoaDon` | `maKhachHang` trong `HoaDon` phải tồn tại trong `KhachHang` |
| `NhanVien → HoaDon` | `maNhanVien` trong `HoaDon` phải tồn tại trong `NhanVien` |
| `KhuyenMai → HoaDon` | `maKhuyenMai` trong `HoaDon` phải tồn tại trong `KhuyenMai` |
| `HoaDon → ChiTietHoaDon` | `maHoaDon` trong `ChiTietHoaDon` phải tồn tại trong `HoaDon` |
| `BienTheSanPham → ChiTietHoaDon` | `maBienThe` trong `ChiTietHoaDon` phải tồn tại trong `BienTheSanPham` |
| `NhaCungCap → PhieuNhap` | `maNhaCungCap` trong `PhieuNhap` phải tồn tại trong `NhaCungCap` |
| `NhanVien → PhieuNhap` | `maNhanVien` trong `PhieuNhap` phải tồn tại trong `NhanVien` |
| `PhieuNhap → ChiTietPhieuNhap` | `maPhieuNhap` trong `ChiTietPhieuNhap` phải tồn tại trong `PhieuNhap` |
| `BienTheSanPham → ChiTietPhieuNhap` | `maBienThe` trong `ChiTietPhieuNhap` phải tồn tại trong `BienTheSanPham` |

---

## Câu 4. Các câu truy vấn

Các thành viên trong nhóm trao đổi và xây dựng các câu truy vấn dựa trên các thực thể (ERs) và mối quan hệ (RRs) đã được xác định trong hệ thống. Các câu truy vấn được phân chia theo từng nhóm thực thể mà mỗi thành viên phụ trách.

## 4.1. Thành viên 1 — Danh mục, Sản phẩm, Biến thể sản phẩm

| Câu | Nội dung truy vấn |
|---:|---|
| 1 | Liệt kê tất cả danh mục sản phẩm, gồm mã danh mục, tên danh mục và mô tả. |
| 2 | Liệt kê tất cả sản phẩm, gồm mã sản phẩm, tên sản phẩm, thương hiệu, giới tính và giá bán. |
| 3 | Tìm các sản phẩm có giá bán trong khoảng từ 300.000 đến 700.000 đồng. |
| 4 | Liệt kê các sản phẩm dành cho nam và sắp xếp theo giá bán giảm dần. |
| 5 | Tìm các biến thể sản phẩm có số lượng tồn kho dưới 10 sản phẩm. |
| 6 | Liệt kê sản phẩm cùng với tên danh mục mà sản phẩm đó thuộc về. |
| 7 | Liệt kê sản phẩm và các biến thể tương ứng, gồm tên sản phẩm, kích cỡ, màu sắc và số lượng tồn. |
| 8 | Tìm các sản phẩm thuộc một danh mục cụ thể, ví dụ danh mục “Áo”. |
| 9 | Thống kê số lượng biến thể của từng sản phẩm. |
| 10 | Tìm các sản phẩm có tổng số lượng tồn của tất cả biến thể lớn hơn 20 sản phẩm. |

---

## 4.2. Thành viên 2 — Khách hàng, Hóa đơn, Chi tiết hóa đơn, Khuyến mãi

| Câu | Nội dung truy vấn |
|---:|---|
| 1 | Liệt kê tất cả khách hàng, gồm mã khách hàng, họ tên, số điện thoại, email và hạng thành viên. |
| 2 | Tìm các khách hàng thuộc hạng thành viên Vàng hoặc Kim cương. |
| 3 | Liệt kê các hóa đơn đã thanh toán, gồm mã hóa đơn, ngày lập, tổng tiền, tiền giảm và thành tiền. |
| 4 | Liệt kê hóa đơn cùng với tên khách hàng đã mua và nhân viên lập hóa đơn. |
| 5 | Xem chi tiết một hóa đơn cụ thể, gồm mã hóa đơn, tên sản phẩm, kích cỡ, màu sắc, số lượng và đơn giá. |
| 6 | Tính tổng số tiền hàng của từng hóa đơn dựa trên số lượng và đơn giá trong chi tiết hóa đơn. |
| 7 | Tính tổng doanh thu từ các hóa đơn đã thanh toán. |
| 8 | Thống kê số lượng hóa đơn và tổng doanh thu theo từng khách hàng. |
| 9 | Tìm các khách hàng chưa từng có hóa đơn đã thanh toán. |
| 10 | Tìm các hóa đơn có thành tiền lớn hơn giá trị thành tiền trung bình của tất cả các hóa đơn. |

---

## 4.3. Thành viên 3 — Nhân viên, Nhà cung cấp, Phiếu nhập, Chi tiết phiếu nhập

| Câu | Nội dung truy vấn |
|---:|---|
| 1 | Liệt kê tất cả nhân viên, gồm mã nhân viên, họ tên, số điện thoại, chức vụ và lương cơ bản. |
| 2 | Tìm các nhân viên thuộc một chức vụ cụ thể. |
| 3 | Liệt kê tất cả nhà cung cấp, gồm mã nhà cung cấp, tên nhà cung cấp, số điện thoại và địa chỉ. |
| 4 | Liệt kê các phiếu nhập cùng với tên nhà cung cấp và nhân viên lập phiếu. |
| 5 | Xem chi tiết một phiếu nhập cụ thể, gồm mã phiếu nhập, sản phẩm, biến thể, số lượng và giá nhập. |
| 6 | Tính tổng tiền nhập của từng phiếu nhập dựa trên số lượng và giá nhập trong chi tiết phiếu nhập. |
| 7 | Tính tổng số tiền hàng đã nhập từ mỗi nhà cung cấp. |
| 8 | Thống kê số lượng phiếu nhập và tổng tiền nhập do từng nhân viên lập. |
| 9 | Tìm các biến thể sản phẩm đã từng được nhập nhưng hiện tại có số lượng tồn bằng 0. |
| 10 | Tìm nhà cung cấp có tổng giá trị nhập hàng lớn hơn giá trị nhập hàng trung bình của tất cả nhà cung cấp. |

---

## 4.4. Tổng hợp các câu truy vấn

| Thành viên | Phần phụ trách | Số câu truy vấn |
|---|---|---:|
| Thành viên 1 | Danh mục, Sản phẩm, Biến thể sản phẩm | 10 |
| Thành viên 2 | Khách hàng, Hóa đơn, Chi tiết hóa đơn, Khuyến mãi | 10 |
| Thành viên 3 | Nhân viên, Nhà cung cấp, Phiếu nhập, Chi tiết phiếu nhập | 10 |
| **Tổng cộng** | **Toàn bộ hệ thống** | **30** |