using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using System.Windows;
using System.Collections.ObjectModel;
using Microsoft.Win32;
using System.IO;
using System.Windows.Controls;
using System.Reflection;
using QuanLyNhaHang.Model;
using System.Windows.Documents;
using System.Windows.Markup;
using System.Windows.Media;
using System.Data;
using System.Text.RegularExpressions;

namespace QuanLyNhaHang.ViewModel
{
    public class MainViewModel : BaseViewModel
    {
        public bool Isloaded = false;
        public ICommand LoadedWindowCommand { get; set; }
        // mọi thứ xử lý sẽ nằm trong này
        public MainViewModel()
        {
            //LoadedWindowCommand = new RelayCommand<Window>((p) => { return true; }, (p) =>
            //{
                
            //    Isloaded = true;
            //    if (p == null)
            //        return;
            //    p.Hide();
            //    Login login = new Login();
            //    login.ShowDialog();

            //    if (login.DataContext == null)
            //        return;
            //    var loginVM = login.DataContext as LoginViewModel;

            //    if (loginVM.IsLogin)
            //    {
            //        p.Show();


            //    }
            //    else
            //    {
            //        p.Close();
            //    }
            //}
            //  );
              
            XuLyDanhSachMonAn();
            XuLyMonAn();
            LoadDanhSachThongKeDoanhThu();
            LoadDanhSachBan();
            LoadDanhSachNhanVien();
            LoadDanhSachMonAn();
            LoadLoaiMonAn();
            XuLyDanhSachLoaiMon();
            XuLyDanhSachBan();
            ThanhToan();
            XuLyDanhSachNhanVien();
            TimKiemThongKeDoanhThu();
            LoadChucVu();
            XuLyDanhSachChucVu();
            LoadDanhSachMonAn1();
        }


        private ObservableCollection<aChucVu> _a1ChucVu;
        public ObservableCollection<aChucVu> a1ChucVu { get => _a1ChucVu; set { _a1ChucVu = value; OnPropertyChanged(); } }

        private ObservableCollection<aLoaiMon> _a1LoaiMon;
        public ObservableCollection<aLoaiMon> a1LoaiMon { get => _a1LoaiMon; set { _a1LoaiMon = value; OnPropertyChanged(); } }

        private ObservableCollection<aDanhSachBan> _a1DanhSachBan;
        public ObservableCollection<aDanhSachBan> a1DanhSachBan { get => _a1DanhSachBan; set { _a1DanhSachBan = value; OnPropertyChanged(); } }

        private ObservableCollection<DANHSACHBAN> _DANHSACHBAN;
        public ObservableCollection<DANHSACHBAN> DANHSACHBAN { get => _DANHSACHBAN; set { _DANHSACHBAN = value; OnPropertyChanged(); } }

        private ObservableCollection<Model.GOIMON> _GOIMON;
        public ObservableCollection<Model.GOIMON> GOIMON { get => _GOIMON; set { _GOIMON = value; OnPropertyChanged(); } }

        private ObservableCollection<Model.MONAN> _MONAN;
        public ObservableCollection<Model.MONAN> MONAN { get => _MONAN; set { _MONAN = value; OnPropertyChanged(); } }


        private ObservableCollection<Model.THONGKEDOANHTHU> _THONGKEDOANHTHU;
        public ObservableCollection<Model.THONGKEDOANHTHU> THONGKEDOANHTHU { get => _THONGKEDOANHTHU; set { _THONGKEDOANHTHU = value; OnPropertyChanged(); } }

        private int _MaBan;
        public int MaBan { get => _MaBan; set { _MaBan = value; OnPropertyChanged(); } }

        private int _SoLuong;
        public int SoLuong { get => _SoLuong; set { _SoLuong = value; OnPropertyChanged(); } }


        private decimal _DonGia;
        public decimal DonGia { get => _DonGia; set { _DonGia = value; OnPropertyChanged(); } }

        private string _ThanhTien;
        public string ThanhTien { get => _ThanhTien; set { _ThanhTien = value; OnPropertyChanged(); } }

        private string _TenMon;
        public string TenMon { get => _TenMon; set { _TenMon = value; OnPropertyChanged(); } }



        /*Quản lí danh sách bàn */
        private string _TenBan;
        public string TenBan { get => _TenBan; set { _TenBan = value; OnPropertyChanged(); } }

        private string _TrangThai;
        public string TrangThai { get => _TrangThai; set { _TrangThai = value; OnPropertyChanged(); } }
        
        private Visibility _TrangThai1;
        public Visibility TrangThai1
        {
            get => _TrangThai1;
            set
            {
                OnPropertyChanged(nameof(_TrangThai1));
                _TrangThai1 = value;
            }
        }

        public void TrangThaiBan()
        {
            if (TrangThai == null)
            {
                TrangThai1 = Visibility.Hidden;
            }
            else
            {
                TrangThai1 = Visibility.Visible;
            }
        }

        private DANHSACHBAN _SelectedItemDanhsachban;
        public DANHSACHBAN SelectedItemDanhsachban
        {
            get => _SelectedItemDanhsachban;
            set
            {

                
                _SelectedItemDanhsachban = value;
                OnPropertyChanged();
                if (SelectedItemDanhsachban != null)
                {
                    
                    MaBan = SelectedItemDanhsachban.MaBan;
                    TenBan = SelectedItemDanhsachban.TenBan.ToString();
                    
                    TrangThai = SelectedItemDanhsachban.TrangThai.ToString();
                    
                }
               

            }
        }
        private aDanhSachBan _SelectedItemDanhsachban1;
        public aDanhSachBan SelectedItemDanhsachban1
        {
            get => _SelectedItemDanhsachban1;
            set
            {


                _SelectedItemDanhsachban1 = value;
                OnPropertyChanged();
                if (SelectedItemDanhsachban1 != null)
                {

                    TenBan = SelectedItemDanhsachban1.DANHSACHBAN.TenBan.ToString();
                }


            }
        }
        public void LoadDanhSachBan()
        {
            DANHSACHBAN = new ObservableCollection<DANHSACHBAN>(DataProvider.Ins.DB.DANHSACHBANs);
            a1DanhSachBan = new ObservableCollection<aDanhSachBan>();
            var a2DanhSachBan = DataProvider.Ins.DB.DANHSACHBANs;
            int i = 1;
            foreach(var item in a2DanhSachBan)
            {
                aDanhSachBan a3DanhSachBan = new aDanhSachBan();
                a3DanhSachBan.STT = i;
                a3DanhSachBan.DANHSACHBAN = item;
                i++;
                a1DanhSachBan.Add(a3DanhSachBan);
            }
        }
        public ICommand AddCommandThemban { get; set; }
        public ICommand EditCommandSuaban { get; set; }
        public ICommand DeleteCommandXoaBan { get; set; }
        public void XuLyDanhSachBan()
        {

            /*Thêm bàn*/
            AddCommandThemban = new RelayCommand<DANHSACHBAN>((p) =>
            {
                return true;
            }, (p) =>
            {

                if (string.IsNullOrEmpty(TenBan) || TenBan.Count()==0 || TenBan.Trim().Length==0)
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báo!");
                }
                else
                {
                        var TenB = new DANHSACHBAN() { TenBan = TenBan, TrangThai = "trong.png" };
                        DataProvider.Ins.DB.DANHSACHBANs.Add(TenB);
                        DataProvider.Ins.DB.SaveChanges();
                        MessageBox.Show(" Bạn đã thêm bàn mới thành công!", "Thông báo!");
                        DANHSACHBAN.Add(TenB);
                        LoadDanhSachBan();
                        TenBan = null;
                }

            });
            /*Sửa bàn*/
            EditCommandSuaban = new RelayCommand<DANHSACHBAN>((p) =>
            {
                
                if (SelectedItemDanhsachban1 == null)
                    return false;
                return true;

            }, (p) =>
            {
                if (string.IsNullOrEmpty(TenBan) || TenBan.Count() == 0 || TenBan.Trim().Length == 0)
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báo!");
                }
                else
                {
                        var MaBan = DataProvider.Ins.DB.DANHSACHBANs.Where(x => x.MaBan == SelectedItemDanhsachban1.DANHSACHBAN.MaBan).SingleOrDefault();
                        MaBan.TenBan = TenBan;
                        DataProvider.Ins.DB.SaveChanges();
                        SelectedItemDanhsachban1.DANHSACHBAN.TenBan = TenBan;
                        MessageBox.Show("Bạn đã cập nhật thông tin bàn thành công!", "Thông báo!");
                        SelectedItemDanhsachban1 = null;
                        TenBan = null;
                        TrangThai = null;
                        LoadDanhSachBan();
                }
            });
            /*Xóa bàn*/
            DeleteCommandXoaBan = new RelayCommand<DANHSACHBAN>((p) =>
            {
                if (SelectedItemDanhsachban1 == null)
                    return false;
                return true;

            }, (p) =>
            {

            });
        }
        /*Kết thúc quản lí danh sách bàn */



        /*Quản lý thanh toán hóa đơn*/
        private int _TongTien;
        public int TongTien { get => _TongTien; set { _TongTien = value; OnPropertyChanged(); } }
        private int _Tien;
        public int Tien { get => _Tien; set { _Tien = value; OnPropertyChanged(); } }

        private DANHSACHBAN _SelectedItem;
        public DANHSACHBAN SelectedItem
        {
            get => _SelectedItem;
            set
            {
                _SelectedItem = value;
                OnPropertyChanged();
                if (SelectedItem != null)
                {
                    TongTien = 0;
                    MaBan = SelectedItem.MaBan;
                    GOIMON = new ObservableCollection<GOIMON>(DataProvider.Ins.DB.GOIMONs.Where(x => x.MaBan == MaBan));
                    
                    MONAN = new ObservableCollection<MONAN>(DataProvider.Ins.DB.MONANs);
                    for (int i = 0; i < GOIMON.Count; i++)
                    {
                        TongTien += int.Parse(GOIMON[i].ThanhTien.ToString());
                    }

                }

            }
        }
        public ICommand DeleteCommand { get; set; }
        public void ThanhToan()
        {
            DeleteCommand = new RelayCommand<GOIMON>((p) =>
            {
                if (SelectedItem == null)
                    return false;

                var MaBan1 = DataProvider.Ins.DB.GOIMONs.Where(x => x.MaBan == SelectedItem.MaBan);
                if (MaBan1.Count() <= 0)
                    return false;

                return true;


            }, (p) =>
            {
                if (String.Format(Tien.ToString())==null)
                {
                    
                    MessageBox.Show("Vui lòng nhập số tiền khách hàng đưa!", "Thông báo!",MessageBoxButton.OK,MessageBoxImage.Question);
                }
                else
                {
                    if (Tien < TongTien)
                    {
                        MessageBox.Show("Vui lòng kiểm tra lại số tiền khách hàng đưa!", "Thông báo!",MessageBoxButton.OK, MessageBoxImage.Warning);
                    }
                    else
                    {
                        if (Tien >= TongTien)
                        {
                            GOIMON = new ObservableCollection<GOIMON>(DataProvider.Ins.DB.GOIMONs.Where(x => x.MaBan == SelectedItem.MaBan));
                            var TrangThai = DataProvider.Ins.DB.DANHSACHBANs.Where(x => x.MaBan == SelectedItem.MaBan).SingleOrDefault();
                            TrangThai.TrangThai = Convert.ToString("trong.png");
                            var ngaythanhtoan = DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x => x.NgayThanhToan == DateTime.Today).SingleOrDefault();
                            if (ngaythanhtoan == null)
                            {
                                var thongkedoanhthu = new THONGKEDOANHTHU() { TongTien = TongTien, NgayThanhToan = DateTime.Today };
                                DataProvider.Ins.DB.THONGKEDOANHTHUs.Add(thongkedoanhthu);
                            }
                            else
                            {
                                ngaythanhtoan.TongTien += TongTien;
                            }
                            
                            int TienDu = Tien - TongTien;
                            
                            string a = "Thành tiền";
                            FlowDocument fd = new FlowDocument();
                            PrintDialog pd = new PrintDialog();
                            fd.PagePadding = new Thickness(50);
                            fd.ColumnGap = 0;
                            fd.ColumnWidth = pd.PrintableAreaWidth;
                            if (pd.ShowDialog() != true) return;
                            fd.Blocks.Add(new Paragraph(new Run(
                                "\t THANH TOÁN HÓA ĐƠN" + "\n\n" +
                                    "Bàn\t\t: " + SelectedItem.TenBan+"\r"+
                                    "Thu ngân\t:\r"+
                                    "T/g thanh toán   \b\b\b :"+DateTime.Now.ToString("dd-MM-yyyy")+"\n\n"))
                                    );
                            for(int i=0;i<GOIMON.Count;i++)
                            {
                                fd.Blocks.Add(new Paragraph(new Run(
                                  MONAN[i].TenMon+"\r\t"+GOIMON[i].SoLuong +"\t\t"+ (String.Format("{0:n0}", GOIMON[i].DonGia))+"\t\t"+ String.Format("{0:n0}", GOIMON[i].ThanhTien)))
                                        );
                            }
                            fd.Blocks.Add(new Paragraph(new Run(
                               a+":\t\t\t\t"+ String.Format("{0:n0}", TongTien)))
                                    );
                            fd.Blocks.Add(new Paragraph(new Run(
                                "\n\n Số tiền khách đưa      : " +
                                (String.Format("{0:n0}",Tien)) + " VNĐ"+
                                "\r Số tiền khách dư        : "+
                                (String.Format("{0:n0}",TienDu)) +" VNĐ"))
                                    );
                            fd.Blocks.Add(new Paragraph(new Run(
                                "\n\n\t\t Thân chào quý khách!\r"))
                                    );

                            IDocumentPaginatorSource idocument = fd as IDocumentPaginatorSource;
                            pd.PrintDocument(idocument.DocumentPaginator, "Printing Flow Document...");
                            DataProvider.Ins.DB.GOIMONs.RemoveRange(GOIMON);
                            DataProvider.Ins.DB.SaveChanges();
                            GOIMON = null;
                            SelectedItem.MaBan = MaBan;
                            TongTien = 0;
                            Tien = 0;
                            MessageBox.Show("Thanh toán thành công!" + "\n \n" + "Số tiền trả lại cho khách hàng là: " + (String.Format("{0:n0}", TienDu)) + " VNĐ", "Thông báo!");

                        }
                    }
                }

            });
        }
        /*Kết thúc quản lý thanh toán hóa đơn*/



        /*Quản lý danh sách nhân viên*/
        private ObservableCollection<NHANVIEN> _NHANVIEN;
        public ObservableCollection<NHANVIEN> NHANVIEN { get => _NHANVIEN; set { _NHANVIEN = value; OnPropertyChanged(); } }

        private ObservableCollection<NHANVIEN> _NHANVIEN1;
        public ObservableCollection<NHANVIEN> NHANVIEN1 { get => _NHANVIEN1; set { _NHANVIEN1 = value; OnPropertyChanged(); } }

        private string _HoTenNV;
        private string _TenDN;
        private string _MatKhau;
        private string _HeThong;
        private string _MaNV;
        public string HoTenNV { get => _HoTenNV; set { _HoTenNV = value; OnPropertyChanged(); } }
        public string TenDN { get => _TenDN; set { _TenDN = value; OnPropertyChanged(); } }
        public string MatKhau { get => _MatKhau; set { _MatKhau = value; OnPropertyChanged(); } }
        public string HeThong { get => _HeThong; set { _HeThong = value; OnPropertyChanged(); } }
        public string MaNV { get => _MaNV; set { _MaNV = value; OnPropertyChanged(); } }

        private NHANVIEN _SelectedItemNV;
        public NHANVIEN SelectedItemNV
        {
            get => _SelectedItemNV;
            set
            {
                _SelectedItemNV = value;
                OnPropertyChanged();
                if (SelectedItemNV != null)
                {
                    MaNV = SelectedItemNV.MaNV.ToString();
                    HoTenNV = SelectedItemNV.HoTenNV.ToString();
                    TenDN = SelectedItemNV.TenDN.ToString();
                    MatKhau = SelectedItemNV.MatKhau.ToString();
                    HeThong = SelectedItemNV.HeThong.ToString();
                }

            }
        }

        public void LoadDanhSachNhanVien()
        {
            NHANVIEN = new ObservableCollection<NHANVIEN>(DataProvider.Ins.DB.NHANVIENs);

        }
        public ICommand AddCommandNhanVien { get; set; }
        public ICommand EditCommandNhanVien { get; set; }
        public ICommand DeleteCommandNhanVien { get; set; }

        public void XuLyDanhSachNhanVien()
        {
            AddCommandNhanVien = new RelayCommand<NHANVIEN>((p) =>
            {
                return true;
            }, (p) =>
            {
                if (string.IsNullOrEmpty(HoTenNV) || string.IsNullOrEmpty(MatKhau) || string.IsNullOrEmpty(TenDN) || string.IsNullOrEmpty(HeThong) || TenDN.Trim().Length==0 || MatKhau.Trim().Length==0 || HoTenNV.Trim().Length==0)
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báp!");
                }
                else
                {
                    if (!Regex.IsMatch(MatKhau, @"^(?=[\040-\176]*?[A-Z])(?=[\040-\176]*?[a-z])(?=[\040-\176]*?[0-9])(?=[\040-\176]*?[#?!@$%^&*-])[\040-\176]{8,72}$"))
                    {
                        MessageBox.Show("Vui lòng nhập mật khẩu đảm bảo có kí tự,hoa,thường!", "Thông báp!");
                    }
                    else
                    {
                        if(!Regex.IsMatch(TenDN, @"^.{8,}$"))
                        {
                            MessageBox.Show("Tên đăng nhập phải trên 8 kí tự!", "Thông báo");
                        }
                        else
                        {
                            if (HeThong.ToString() == "Android" || HeThong.ToString() == "PC" || HeThong.ToString() == "android" || HeThong.ToString() == "pc")
                            {
                                var NhanVien = new NHANVIEN() { HoTenNV = HoTenNV, HeThong = HeThong, TenDN = TenDN, MatKhau = GetMD5(MatKhau), MaChucVu = SelectedItemCHUCVU.MaChucVu };
                                DataProvider.Ins.DB.NHANVIENs.Add(NhanVien);
                                DataProvider.Ins.DB.SaveChanges();
                                NHANVIEN.Add(NhanVien);
                                MessageBox.Show("Bạn đã thêm thành công nhân viên mới!", "Thông báo");
                                HoTenNV = null;
                                TenDN = null;
                                MatKhau = null;
                                HeThong = null;
                                SelectedItemCHUCVU = null;
                            }
                            else
                            {
                                MessageBox.Show("Vui lòng nhập hệ thống là: Android hoặc PC!", "Thông báo");
                            }
                        }
                    }
                }
            });
            EditCommandNhanVien = new RelayCommand<NHANVIEN>((p) =>
            {
                if (SelectedItemNV == null)
                    return false;
                return true;

            }, (p) =>
            {
                if (HeThong.ToString() == "Android" || HeThong.ToString() == "PC" || HeThong.ToString() == "android" || HeThong.ToString() == "pc")
                {
                    var MaNV = DataProvider.Ins.DB.NHANVIENs.Where(x => x.MaNV == SelectedItemNV.MaNV).SingleOrDefault();
                    MaNV.HoTenNV = HoTenNV;
                    MaNV.TenDN = TenDN;
                    MaNV.MatKhau =GetMD5(MatKhau);
                    MaNV.HeThong = HeThong;
                    DataProvider.Ins.DB.SaveChanges();
                    LoadDanhSachNhanVien();
                    MessageBox.Show("Bạn đã cập nhật thông tin nhân viên thành công", "Thông báo!");
                    HoTenNV = null;
                    TenDN = null;
                    MatKhau = null;
                    HeThong = null;
                    SelectedItemNV = null;
                    SelectedItemCHUCVU = null;
                }
                else
                {
                    MessageBox.Show("Vui lòng nhập hệ thống là: Android hoặc PC!", "Thông báo");
                }
            });
            DeleteCommandNhanVien = new RelayCommand<NHANVIEN>((p) =>
            {
                if (SelectedItemNV == null)
                    return false;
                return true;

            }, (p) =>
            {

                NHANVIEN = new ObservableCollection<NHANVIEN>(DataProvider.Ins.DB.NHANVIENs.Where(x => x.MaNV == SelectedItemNV.MaNV));
                DataProvider.Ins.DB.NHANVIENs.RemoveRange(NHANVIEN);
                DataProvider.Ins.DB.SaveChanges();
                LoadDanhSachNhanVien();
                MessageBox.Show("Bạn đã xóa nhân viên thành công", "Thông báo!");
                HoTenNV = null;
                TenDN = null;
                MatKhau = null;
                HeThong = null;
                SelectedItemNV = null;
                SelectedItemCHUCVU = null;
            });
        }


        /*Kết thúc quản lý danh sách nhân viên*/



        /*Quản lý danh sách loại món ăn*/
        private ObservableCollection<LOAIMON> _LOAIMON;
        public ObservableCollection<LOAIMON> LOAIMON { get => _LOAIMON; set { _LOAIMON = value; OnPropertyChanged(); } }

        private string _TenLoaiMon;
        public string TenLoaiMon { get => _TenLoaiMon; set { _TenLoaiMon = value; OnPropertyChanged(); } }



        private int _MaLoaiMon;
        public int MaLoaiMon { get => _MaLoaiMon; set { _MaLoaiMon = value; OnPropertyChanged(); } }


        private LOAIMON _SelectedItemLoaiMonAn;
        public LOAIMON SelectedItemLoaiMonAn
        {
            get => _SelectedItemLoaiMonAn;
            set
            {

                _SelectedItemLoaiMonAn = value;
                OnPropertyChanged();
                if (SelectedItemLoaiMonAn != null)
                {

                    TenLoaiMon = SelectedItemLoaiMonAn.TenLoaiMon.ToString();
                    MaLoaiMon = SelectedItemLoaiMonAn.MaLoaiMon;
                }

            }
        }
        private aLoaiMon _SelectedItemLoaiMonAn1;
        public aLoaiMon SelectedItemLoaiMonAn1
        {
            get => _SelectedItemLoaiMonAn1;
            set
            {

                _SelectedItemLoaiMonAn1 = value;
                OnPropertyChanged();
                if (SelectedItemLoaiMonAn1 != null)
                {

                    TenLoaiMon = SelectedItemLoaiMonAn1.LOAIMON.TenLoaiMon.ToString();
                }

            }
        }
        public ICommand AddCommandLoaiMon { get; set; }
        public ICommand EditCommandSuaMon { get; set; }
        public ICommand DeleteCommandXoaMon { get; set; }

        public void LoadLoaiMonAn()
        {
            LOAIMON = new ObservableCollection<LOAIMON>(DataProvider.Ins.DB.LOAIMONs);
            a1LoaiMon = new ObservableCollection<aLoaiMon>();
            var a2LoaiMon = DataProvider.Ins.DB.LOAIMONs;
            int i = 1;
            foreach (var item in a2LoaiMon)
            {
                aLoaiMon a3LoaiMon = new aLoaiMon();
                a3LoaiMon.STT = i;
                a3LoaiMon.LOAIMON = item;
                i++;
                a1LoaiMon.Add(a3LoaiMon);
            }
        }
        public void XuLyDanhSachLoaiMon()
        {

            /*Thêm loại món ăn*/
            AddCommandLoaiMon = new RelayCommand<LOAIMON>((p) =>
            {
                //if (string.IsNullOrEmpty(TenLoaiMon)||TenLoaiMon == null || TenLoaiMon.Count() == 0)
                //    return false;
                    return true;

            }, (p) =>
            {
                if (string.IsNullOrEmpty(TenLoaiMon) || TenLoaiMon == null || TenLoaiMon.Count() == 0 || TenLoaiMon.Trim().Length==0)
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập vào!", "Thông báo! ");
                }
                else
                {
                    var TenLM = new LOAIMON() { TenLoaiMon = TenLoaiMon };
                    DataProvider.Ins.DB.LOAIMONs.Add(TenLM);
                    DataProvider.Ins.DB.SaveChanges();
                    MessageBox.Show("Bạn đã thêm loại món mới thành công!", "Thông báo!");
                    LOAIMON.Add(TenLM);
                    TenLoaiMon = null;
                    LoadLoaiMonAn();
                }
                
                
            });
            /*Sửa loại món ăn*/
            EditCommandSuaMon = new RelayCommand<LOAIMON>((p) =>
            {
                if (SelectedItemLoaiMonAn1 == null)
                    return false;
                return true;

            }, (p) =>
            {
                var MaLoaiMon = DataProvider.Ins.DB.LOAIMONs.Where(x => x.MaLoaiMon == SelectedItemLoaiMonAn1.LOAIMON.MaLoaiMon).SingleOrDefault();
                MaLoaiMon.TenLoaiMon = TenLoaiMon;
                DataProvider.Ins.DB.SaveChanges();
                MessageBox.Show( "Bạn đã cập nhật thông tin loại món thành công!", "Thông báo!");
                SelectedItemLoaiMonAn1.LOAIMON.TenLoaiMon = TenLoaiMon;
                SelectedItemLoaiMonAn1 = null;
                TenLoaiMon = null;
                LoadLoaiMonAn();
            });
            /*Xóa loại món ăn*/
            DeleteCommandXoaMon = new RelayCommand<LOAIMON>((p) =>
            {
                if (SelectedItemLoaiMonAn1 == null)
                    return false;
                return true;

            }, (p) =>
            {
                
                //var MaLoaiMon = DataProvider.Ins.DB.LOAIMONs.Where(x => x.MaLoaiMon == SelectedItemLoaiMonAn.MaLoaiMon).SingleOrDefault();
                //MaLoaiMon.TenLoaiMon = TenLoaiMon;
                //DataProvider.Ins.DB.SaveChanges();

                //SelectedItemLoaiMonAn.TenLoaiMon = TenLoaiMon;
                //LoadLoaiMonAn();
            });
        }
        /*Kết thúc quản lý danh sách loại món ăn*/



        /*Quản lý danh sách món ăn*/
        private ObservableCollection<MONAN> _MONAN1;
        public ObservableCollection<MONAN> MONAN1 { get => _MONAN1; set { _MONAN1 = value; OnPropertyChanged(); } }
        

        private string _MaMon;
        public string MaMon { get => _MaMon; set { _MaMon = value; OnPropertyChanged(); } }

        private MonAn _SelectedItemMonAn;
        public MonAn SelectedItemMonAn
        {
            get => _SelectedItemMonAn;
            set
            {

                _SelectedItemMonAn = value;
                OnPropertyChanged();
                if (SelectedItemMonAn != null)
                {
                    TenMon = SelectedItemMonAn.aTenMon;
                    DonGia =Decimal.Parse(SelectedItemMonAn.aDonGia.ToString());
                    
                   
                }
            }
        }


        private ObservableCollection<MonAn> _aMonAn;
        public ObservableCollection<MonAn> aMonAn { get=>_aMonAn; set { _aMonAn = value;OnPropertyChanged(); } }
        void LoadDanhSachMonAn1()
        {
            aMonAn = new ObservableCollection<MonAn>();
            var aMonAn1 = DataProvider.Ins.DB.MONANs;

            string duongdanhinhanh = String.Format(System.IO.Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location + System.IO.Path.DirectorySeparatorChar));
            string[] aduongdanhinhanh = duongdanhinhanh.Split('b');
            foreach (var item in aMonAn1)
            {
                string HinhAnh = null;
                Decimal DonGia; ;
                string TenMon = null;
                HinhAnh = aduongdanhinhanh[0]+item.HinhAnh;
                DonGia =Decimal.Parse((item.DonGia).ToString());
                TenMon = item.TenMon;
                MonAn monan = new MonAn();
                monan.aDonGia = DonGia;
                monan.aHinhAnh = HinhAnh;
                monan.aTenMon = TenMon;
                monan.aMaLoaiMon = MaLoaiMon;
                monan.Object = item;
                aMonAn.Add(monan);
            }
        }

        private string _HinhAnh11;
        public string HinhAnh11 { get => _HinhAnh11; set { _HinhAnh11 = value; OnPropertyChanged(); } }
        public void LoadDanhSachMonAn()
        {
            MONAN1 = new ObservableCollection<MONAN>(DataProvider.Ins.DB.MONANs);
        }
        public ICommand AddCommandMonAn { get; set; }
        public ICommand EditCommandMonAn { get; set; }
        public ICommand DeleteCommandMonAn { get; set; }
        public ICommand AddCommandHinhAnh { get; set; }
        public ICommand OpenCommandHinhAnh { get; set; }

        private Image _DuongDanHinhAnh;
        public Image DuongDanHinhAnh { get => _DuongDanHinhAnh; set { _DuongDanHinhAnh = value; OnPropertyChanged(); } }

        private byte[] _AddHinhAnh;
        public byte[] AddHinhAnh { get => _AddHinhAnh; set { _AddHinhAnh = value; OnPropertyChanged(); } }

        private string _HinhAnh;
        public string HinhAnh { get => _HinhAnh; set { _HinhAnh = value; OnPropertyChanged(); } }


        public void XuLyDanhSachMonAn()
        {

            OpenCommandHinhAnh = new RelayCommand<MONAN>((p) =>
            {
                return true;

            }, (p) =>
            {
                OpenFileDialog open = new OpenFileDialog();
                open.Filter = "jpg(*.jpg)|*.jpg";
                if (open.ShowDialog() == true)
                {
                    HinhAnh = open.FileName;
                    AddHinhAnh = File.ReadAllBytes(open.FileName);

                }
            });
        }


        private void XuLyMonAn()
        {
            AddCommandMonAn = new RelayCommand<MONAN>((p) =>
            {
                return true;
            }, (p) =>
            {
                if(TenMon.Trim().Length==0||DonGia<1||SelectedItemLoaiMonAn==null)
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báo!");
                }
                else
                {
                    try
                    {
                        string duongdanhinhanh = String.Format(System.IO.Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location + System.IO.Path.DirectorySeparatorChar));
                        string[] aduongdanhinhanh = duongdanhinhanh.Split('b');
                        File.Copy(HinhAnh, Path.Combine(aduongdanhinhanh[0], Path.GetFileName(HinhAnh)), true);
                        var MonAn = new MONAN() { TenMon = TenMon, MaLoaiMon = SelectedItemLoaiMonAn.MaLoaiMon, DonGia = DonGia, HinhAnh = Path.GetFileName(HinhAnh) };
                        DataProvider.Ins.DB.MONANs.Add(MonAn);
                        DataProvider.Ins.DB.SaveChanges();
                        MessageBox.Show("Bạn đã thêm món thành công!");
                        LoadDanhSachMonAn1();
                        TenMon = null;
                        DonGia = 0;
                        AddHinhAnh = null;
                        SelectedItemLoaiMonAn = null;
                    }
                    catch(Exception)
                    {
                        MessageBox.Show("Hình ảnh đã tồn tại!","Thông báo!");
                    }
                   
                }
               
            });
            EditCommandMonAn = new RelayCommand<MONAN>((p) =>
            {
                if (SelectedItemMonAn == null)
                    return false;
                return true;
            }, (p) =>
            {
                if (TenMon.Trim().Length == 0 || DonGia > 0 || SelectedItemLoaiMonAn == null || AddHinhAnh == null)
                {
                    string duongdanhinhanh = String.Format(System.IO.Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location + System.IO.Path.DirectorySeparatorChar));
                    string[] aduongdanhinhanh = duongdanhinhanh.Split('b');
                    File.Copy(HinhAnh, Path.Combine(aduongdanhinhanh[0], Path.GetFileName(HinhAnh)), true);
                    var MonAn = new MONAN() { TenMon = TenMon, MaLoaiMon = SelectedItemLoaiMonAn.MaLoaiMon, DonGia = DonGia, HinhAnh = Path.GetFileName(HinhAnh) };
                    DataProvider.Ins.DB.MONANs.Add(MonAn);
                    DataProvider.Ins.DB.SaveChanges();
                    MessageBox.Show("Bạn đã thêm món thành công!");
                    LoadDanhSachMonAn1();
                    TenMon = null;
                    DonGia = 0;
                    AddHinhAnh = null;
                    SelectedItemLoaiMonAn = null;
                }
                else
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báo!");
                }

            });
            DeleteCommandMonAn = new RelayCommand<MONAN>((p) =>
            {
                if (SelectedItemMonAn == null)
                    return false;
                return true;
            }, (p) =>
            {
                if (TenMon.Trim().Length == 0 || DonGia > 0 || SelectedItemLoaiMonAn == null || AddHinhAnh == null)
                {
                    string duongdanhinhanh = String.Format(System.IO.Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location + System.IO.Path.DirectorySeparatorChar));
                    string[] aduongdanhinhanh = duongdanhinhanh.Split('b');
                    File.Copy(HinhAnh, Path.Combine(aduongdanhinhanh[0], Path.GetFileName(HinhAnh)), true);
                    var MonAn = new MONAN() { TenMon = TenMon, MaLoaiMon = SelectedItemLoaiMonAn.MaLoaiMon, DonGia = DonGia, HinhAnh = Path.GetFileName(HinhAnh) };
                    DataProvider.Ins.DB.MONANs.Add(MonAn);
                    DataProvider.Ins.DB.SaveChanges();
                    MessageBox.Show("Bạn đã thêm món thành công!");
                    LoadDanhSachMonAn1();
                    TenMon = null;
                    DonGia = 0;
                    AddHinhAnh = null;
                    SelectedItemLoaiMonAn = null;
                }
                else
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báo!");
                }

            });
        }
        /*Kết thúc quản lý danh sách món ăn*/



        /*Quản lý danh sách chức vụ*/
        private ObservableCollection<CHUCVU> _CHUCVU;
        public ObservableCollection<CHUCVU> CHUCVU { get => _CHUCVU; set { _CHUCVU = value; OnPropertyChanged(); } }

        private string _TenChucVu;
        public string TenChucVu { get => _TenChucVu; set { _TenChucVu = value; OnPropertyChanged(); } }

        private CHUCVU _SelectedItemCHUCVU;
        public CHUCVU SelectedItemCHUCVU
        {
            get => _SelectedItemCHUCVU;
            set
            {


                _SelectedItemCHUCVU = value;
                OnPropertyChanged();
                if (SelectedItemCHUCVU != null)
                {
                    TenChucVu = SelectedItemCHUCVU.ChucVu1.ToString();
                }


            }
        }

        private aChucVu _SelectedItemCHUCVU1;
        public aChucVu SelectedItemCHUCVU1
        {
            get => _SelectedItemCHUCVU1;
            set
            {


                _SelectedItemCHUCVU1 = value;
                OnPropertyChanged();
                if (SelectedItemCHUCVU1 != null)
                {
                    TenChucVu = SelectedItemCHUCVU1.CHUCVU.ChucVu1.ToString();
                }


            }
        }

        public ICommand AddCommandCHUCVU { get; set; }
        public ICommand EditCommandCHUCVU { get; set; }
        public ICommand DeleteCommandCHUCVU { get; set; }

        public void LoadChucVu()
        {
            CHUCVU = new ObservableCollection<CHUCVU>(DataProvider.Ins.DB.CHUCVUs);
            a1ChucVu = new ObservableCollection<aChucVu>();
            var a2ChucVu = DataProvider.Ins.DB.CHUCVUs;
            int i = 1;
            foreach (var item in a2ChucVu)
            {
                aChucVu a3ChucVu = new aChucVu();
                a3ChucVu.STT = i;
                a3ChucVu.CHUCVU = item;
                i++;
                a1ChucVu.Add(a3ChucVu);
            }
        }

        public void XuLyDanhSachChucVu()
        {

            /*Thêm chức vụ*/
            AddCommandCHUCVU = new RelayCommand<CHUCVU>((p) =>
            {
                return true;

            }, (p) =>
            {
                if (string.IsNullOrEmpty(TenChucVu) || TenChucVu == null || TenChucVu.Count() == 0 || TenChucVu.Trim().Length == 0)
                {
                    MessageBox.Show("Vui lòng kiểm tra thông tin nhập!", "Thông báo! ");
                }
                else
                {
                    var TenChucVu1 = new CHUCVU() { ChucVu1= TenChucVu };
                    DataProvider.Ins.DB.CHUCVUs.Add(TenChucVu1);
                    DataProvider.Ins.DB.SaveChanges();
                    MessageBox.Show("Bạn đã thêm chức vụ mới thành công!", "Thông báo!");
                    CHUCVU.Add(TenChucVu1);
                    TenChucVu = null;
                    LoadChucVu();
                }


            });
            /*Sửa chức vụ*/
            EditCommandCHUCVU = new RelayCommand<CHUCVU>((p) =>
            {
                if (SelectedItemCHUCVU1 == null)
                    return false;
                return true;

            }, (p) =>
            {
                var MaChucVu = DataProvider.Ins.DB.CHUCVUs.Where(x => x.MaChucVu == SelectedItemCHUCVU1.CHUCVU.MaChucVu).SingleOrDefault();
                MaChucVu.ChucVu1 = TenChucVu;
                DataProvider.Ins.DB.SaveChanges();
                MessageBox.Show("Bạn đã cập nhật thông tin chức vụ thành công!", "Thông báo!");
                LoadChucVu();
                SelectedItemCHUCVU1 = null;
                TenChucVu = null;
            });
            /*Xóa chức vụ*/
            DeleteCommandCHUCVU = new RelayCommand<LOAIMON>((p) =>
            {
                if (SelectedItemCHUCVU1 == null)
                    return false;
                return true;

            }, (p) =>
            {
                //var MaLoaiMon = DataProvider.Ins.DB.LOAIMONs.Where(x => x.MaLoaiMon == SelectedItemLoaiMonAn.MaLoaiMon).SingleOrDefault();
                //MaLoaiMon.TenLoaiMon = TenLoaiMon;
                //DataProvider.Ins.DB.SaveChanges();

                //SelectedItemLoaiMonAn.TenLoaiMon = TenLoaiMon;
                //LoadLoaiMonAn();
            });
        }
        /*Kết thúc quản lý chức vụ*/



        /*Quản lý danh sách thống kê doanh thu*/
        private ObservableCollection<THONGKEDOANHTHU> _thongkedoanhthu;
        public ObservableCollection<THONGKEDOANHTHU> thongkedoanhthu { get => _thongkedoanhthu; set { _thongkedoanhthu = value; OnPropertyChanged(); } }

        private DateTime _NgayBatDauThongKeDoanhThu;
        public DateTime NgayBatDauThongKeDoanhThu { get => _NgayBatDauThongKeDoanhThu; set { _NgayBatDauThongKeDoanhThu = value; OnPropertyChanged(); } }

        private DateTime _NgayKetThucThongKeDoanhThu;
        public DateTime NgayKetThucThongKeDoanhThu { get => _NgayKetThucThongKeDoanhThu; set { _NgayKetThucThongKeDoanhThu = value; OnPropertyChanged(); } }

        private int _TongTienDoanhThu;
        public int TongTienDoanhThu { get => _TongTienDoanhThu; set { _TongTienDoanhThu = value; OnPropertyChanged(); } }

        private string _TongTienDoanhThuLa;

        public string TongTienDoanhThuLa { get => _TongTienDoanhThuLa; set { _TongTienDoanhThuLa = value; OnPropertyChanged(); } }


        public void LoadDanhSachThongKeDoanhThu()
        {
            thongkedoanhthu = new ObservableCollection<THONGKEDOANHTHU>(DataProvider.Ins.DB.THONGKEDOANHTHUs);
        }
        public ICommand TimKiem { get; set; }
        public ICommand InThongKeDoanhThu { get; set; }
        public void TimKiemThongKeDoanhThu()
        {
            NgayBatDauThongKeDoanhThu = DateTime.Today;
            NgayKetThucThongKeDoanhThu = DateTime.Today;
            TimKiem = new RelayCommand<THONGKEDOANHTHU>((p) =>
            {
                return true;
            }, (p) =>
            {
                TongTienDoanhThuLa = null;
                DateTime NgayKetThuc = DateTime.Parse(NgayKetThucThongKeDoanhThu.ToString());
                DateTime NgayBatDau = DateTime.Parse((NgayBatDauThongKeDoanhThu.ToString()));
                if(NgayBatDauThongKeDoanhThu<=NgayKetThucThongKeDoanhThu)
                {
                    var ngaythanhtoan = DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x => x.NgayThanhToan >= NgayBatDau && x.NgayThanhToan <= NgayKetThuc);
                    if (ngaythanhtoan.Count() > 0)
                    {
                        TongTienDoanhThu = 0;
                        thongkedoanhthu = new ObservableCollection<THONGKEDOANHTHU>(DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x => x.NgayThanhToan >= NgayBatDau && x.NgayThanhToan <= NgayKetThuc));
                        for (int i = 0; i < thongkedoanhthu.Count(); i++)
                        {
                            TongTienDoanhThu += int.Parse(thongkedoanhthu[i].TongTien.ToString());
                        }

                        MessageBox.Show("Tổng số tiền cần thống kê doanh thu là: " + (String.Format("{0:n0}", TongTienDoanhThu)) + " VNĐ", "Thông báo!");
                        TongTienDoanhThuLa = "Tổng số tiền doanh thu từ ngày " + string.Format("{0:dd/MM/yyyy}", NgayBatDau) + " đến " + string.Format("{0:dd/MM/yyyy}", NgayKetThuc) + " là: " + (String.Format("{0:n0}", TongTienDoanhThu)) + " VNĐ";

                    }
                    else
                    {
                        thongkedoanhthu = null;
                        MessageBox.Show("Thống kê chưa có doanh thu!", "Thông báo!");
                    }
                }
                else
                {
                    MessageBox.Show("Vui lòng chọn ngày bắt đầu > ngày kết thúc!", "Thông báo!");
                }
            });
            InThongKeDoanhThu = new RelayCommand<THONGKEDOANHTHU>((p) =>
            {
                return true;
            }, (p) =>
            {   
                DateTime NgayKetThuc = DateTime.Parse(NgayKetThucThongKeDoanhThu.ToString());
                DateTime NgayBatDau = DateTime.Parse((NgayBatDauThongKeDoanhThu.ToString()));

                FlowDocument fd = new FlowDocument();
                PrintDialog pd = new PrintDialog();
                fd.PagePadding = new Thickness(50);
                fd.ColumnGap = 0;
                fd.ColumnWidth = pd.PrintableAreaWidth;
                if (pd.ShowDialog() != true) return;
                fd.Blocks.Add(new Paragraph(new Run(
                        "\t\t\t\t Nhà Hàng Minh Lượng" + "\n" +
                        "\t\t\tĐịa chỉ: Vĩnh Hòa - Phú Giáo - Bình Dương" + "\n\n\n" +
                        "\t\t\t\t Thống kê doanh thu" + "\n\n" +
                        "\t\t\tThời gian:" + "Từ " + string.Format("{0:dd/MM/yyyy}", NgayBatDau) + " đến " + string.Format("{0:dd/MM/yyyy}", NgayKetThuc) + "\n\n" +
                        "\t\t\tSTT" + "\t" + "Ngày doanh thu" + "\t" + "Tiền doanh thu theo ngày"))
                        );
                thongkedoanhthu = new ObservableCollection<THONGKEDOANHTHU>(DataProvider.Ins.DB.THONGKEDOANHTHUs.Where(x => x.NgayThanhToan >= NgayBatDau && x.NgayThanhToan <= NgayKetThuc));
                for (int i = 0; i < thongkedoanhthu.Count(); i++)
                {
                    TongTienDoanhThu += int.Parse(thongkedoanhthu[i].TongTien.ToString());
                    fd.Blocks.Add(new Paragraph(new Run(
                        "\t\t\t" + (i+1)+"\t"+string.Format("{0:dd/MM/yyyy}", thongkedoanhthu[i].NgayThanhToan) + "\t"+ (String.Format("{0:n0}", thongkedoanhthu[i].TongTien)+" VNĐ")
                        ))
                        );
                }
                fd.Blocks.Add(new Paragraph(new Run(
                       "\n\nTổng số tiền doanh thu từ ngày " + string.Format("{0:dd/MM/yyyy}", NgayBatDau) + " đến " + string.Format("{0:dd/MM/yyyy}", NgayKetThuc) + " là: " + (String.Format("{0:n0}", TongTienDoanhThu)) + " VNĐ"
                        ))
                        );
                IDocumentPaginatorSource idocument = fd as IDocumentPaginatorSource;
                pd.PrintDocument(idocument.DocumentPaginator, "Printing Flow Document...");
                MessageBox.Show("Bạn đã in thống kê doanh thu thành công!","Thông báo!");
            });
            /*Kết thúc quản lý thống kê doanh thu*/
        }
        public string GetMD5(string MD5)
        {
            string str = "";
            byte[] A = System.Text.Encoding.UTF8.GetBytes(MD5);
            System.Security.Cryptography.MD5CryptoServiceProvider md5 = new System.Security.Cryptography.MD5CryptoServiceProvider();
            A = md5.ComputeHash(A);
            foreach (Byte b in A)
            {
                str += b.ToString("X2");
            }
            return str;
        }
    }
}
