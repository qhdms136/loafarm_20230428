<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div>
	<div class="img-size"></div>
</div>
<div class="d-flex justify-content-center">
	<div class="loa-hot-board">
		<div class="ml-3 mt-2">
			<span class="lf-hot">�ξ��� ȭ����</span> <span class="lf-today">������
				ȭ��</span>
			<button class="btn-sm btn-dark"
				style="font-size: 12px; margin-left: 40px;">30�� ������</button>
		</div>
		<div class="today-content">
			<ul class="today-list">
			<c:forEach items="${freePostList}" begin="0" end="9" var="free">
				<li>
					<a href="#">
						<span class="cate text-primary">${free.freepost.category}</span>
						<span class="txt">${free.freepost.subject}</span>
					</a>
				</li>
			</c:forEach>	
			</ul>
		</div>
	</div>
</div>
<div class="content2">
	<span class="cm-subject">������ ���� ĳ���� �ٹ̱�!</span>
	<div class="conent2-1 d-flex justify-content-center mt-4">
		<div class="custom-best-box">
			<div class="mt-4 d-flex justify-content-center">
				<img src="/static/img/index/custom.jpg" alt="Ŀ���͸���¡" width="250"
					height="250">
			</div>
			<div class="custom-info-box d-flex justify-content-center">
				<div class="card">
					<div class="card-subject mt-2 ml-2">�� Ŀ���͸���¡ �Դϴ�~~</div>
					<div class="card-nickname my-2 ml-2">aaaa</div>
					<div class="mb-3 d-flex justify-content-end">
						<img src="/static/img/index/recommend.png" alt="��õ" width="25">
						<span class="mx-3">77</span>
					</div>
				</div>
			</div>
		</div>
		<div class="custom-best-box"></div>
		<div class="custom-best-box"></div>
	</div>
</div>
<div class="content3 d-flex">
	<div class="article-left">
		<div
			class="article-title d-flex justify-content-between align-items-center">
			<a href="#" class="notice-title">����</a>
			<button class="mr-5 btn-sm btn-dark">������</button>
		</div>
		<div class="d-flex justify-content-center">
			<div class="article-tb mt-3">
				<table class="table text-center">
					<tbody>
						<tr>
							<td>����</td>
							<td>�����Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>����</td>
							<td>�����Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>����</td>
							<td>�����Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>����</td>
							<td>�����Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>����</td>
							<td>�����Դϴ�.~~~~~</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="article-right">
		<div
			class="article-title d-flex justify-content-between align-items-center">
			<a href="#" class="guild-title">������</a>
			<button class="mr-5 btn-sm btn-dark">������</button>
		</div>
		<div class="d-flex justify-content-center">
			<div class="article-tb mt-3">
				<table class="table text-center">
					<tbody>
						<tr>
							<td>������</td>
							<td>�������Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>������</td>
							<td>�������Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>������</td>
							<td>�������Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>������</td>
							<td>�������Դϴ�.~~~~~</td>
						</tr>
						<tr>
							<td>������</td>
							<td>�������Դϴ�.~~~~~</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>