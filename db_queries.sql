select m.name, r.user_token, s.seat_number, sk.sku, sk.price, sc.name, th.name, th.city, th.zip_code, sk.type, r.status  
from reservation r 
join reservation_item ri  
on ri.reservation_id = r.id 
join movie_schedule ms on ms.id = r.schedule_id 
join movie m on m.id=ms.movie_id 
join screen sc on ms.screen_id=sc.id 
join seat s on s.screen_id=sc.id and ri.seat_id=s.id 
join sku sk on sk.id = ri.sku_id 
join theater th on th.id=sc.theater_id;



select * from movie_schedule ms 
join screen s on s.id=ms.screen_id 
join seat se on se.screen_id=s.id
join reservation r on r.schedule_id=
where ms.id='{scheule_id}'


