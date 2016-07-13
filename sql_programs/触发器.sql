--1.创建触发器
create trigger trigger_1
on 处方信息
after update
as
begin
	update 药房
	set 药房药品数量 = 药房药品数量 - (select 药品数量 from updated )
	where 药房药品编号=(select i药品编号 from updated)
end