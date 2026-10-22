
package clothing.dao;

import clothing.dao.NhanVienDAO;
import clothing.data.DataStore;
import clothing.model.NhanVien;
import clothing.model.chucVu;

import java.math.BigDecimal;
import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        NhanVienDAO dao = new NhanVienDAO(dataStore);

        System.out.println("Số nhân viên ban đầu: "
                + dao.layTatCa().size());

        NhanVien nvMoi = new NhanVien(
                "NV_TEST",
                "Nguyễn Văn An",
                "0901234567",
                null, // Thay bằng giá trị enum chucVu hợp lệ nếu bắt buộc
                new BigDecimal("8000000"),
                LocalDate.of(2026, 1, 1)
        );

        // 1. Thêm mới
        System.out.println("1. Thêm mới: " + dao.them(nvMoi));

        // 2. Thêm trùng mã
        System.out.println("2. Thêm trùng mã: " + dao.them(nvMoi));

        // 3. Thêm null
        System.out.println("3. Thêm null: " + dao.them(null));

        // 4. Tìm theo mã
        System.out.println("4. Tìm NV_TEST: "
                + (dao.timTheoMa("NV_TEST") != null));

        // 5. Xóa nhân viên vừa thêm
        System.out.println("5. Xóa NV_TEST: " + dao.xoa("NV_TEST"));

        // 6. Xóa mã không tồn tại
        System.out.println("6. Xóa mã không tồn tại: "
                + dao.xoa("NV_KHONG_TON_TAI"));
    }
}
