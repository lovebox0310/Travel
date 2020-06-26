<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<ul class="pagination justify-content-center" style="margin: 10px 0">
		<li>
			<select class="custom-select">
				<option >제목</option>	
			</select>
		</li>
		&nbsp;
		<li>
			<input name="locationID" value="${nowLocationID }" hidden="true">
			<input name="searchTitle" value="${searchTitle }" style="width: 400px;" class="form-control input-lg" type="text" placeholder="[ 맛있게 먹으면 칼로리 0 ] 많은 공유 부탁드립니다!">
		</li>
		&nbsp;
		<li>
			<button class="btn btn-outline-primary" type="submit">
				<i class="fas fa-search"></i>
			</button>
		</li>
	</ul>
