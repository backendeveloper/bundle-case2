1. Random veri üreten bir kaynak uygulama yazılacak:
   Bu verinin içinde
   timestamp,
   0-100 arasında random integer,
   üstteki iki verinin md5 hash'inin son 2 karakteri
   Saniyede 5 değer üretecek.
   Üretilen değerler bir socket'e yazılacak.

2. Dinleyen socket tarafındaki uygulama filtreleme işlemi yapacak:
   İkinci alandaki değer 90'ın üstündeyse diğer alanlarla beraber bir message queue'ye gönderilecek.
   Değilse bir file'a append yapacak şekilde yazacak.

3. Message Queue'yi dinleyen iki uygulama yazılacak:
   Birinci uygulama MQ'dan aldığı değerleri bir database tablosuna yazacak. Insert dışında Database tarafının kodlanmasına gerek yok.
   İkinci uygulama aynı mesajı bir mongo collection'a yazacak. Ardışık gelen hash alanları yalnız "99"dan büyükse aynı kayıt içinde nested saklanacak.
   Değilse yeni kayıt oluşturulacak.


