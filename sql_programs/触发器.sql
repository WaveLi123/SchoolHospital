--1.����������
create trigger trigger_1
on ������Ϣ
after update
as
begin
	update ҩ��
	set ҩ��ҩƷ���� = ҩ��ҩƷ���� - (select ҩƷ���� from updated )
	where ҩ��ҩƷ���=(select iҩƷ��� from updated)
end