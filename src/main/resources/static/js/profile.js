/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 구독하기, 구독취소
 (2) 구독자 정보 모달 보기
 (3) 구독자 정보 모달에서 구독하기, 구독취소
 (4) 유저 프로필 사진 변경
 (5) 사용자 정보 메뉴 열기 닫기
 (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
 (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
 (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(toUserId, obj) {
	if ($(obj).text() === "구독취소") {
		console.log("구독취소", toUserId);
		$.ajax({
			type: "POST",
			url: "/api/unSubscribe/" + toUserId,
			dataType: "JSON"
		}).done(res => {
			console.log(res);
			$(obj).text("구독하기");
			$(obj).toggleClass("blue");
		}).fail(error => {
			console.log(error);
			alert("구독취소 실패");
		});
	} else {
		console.log("구독하기", toUserId);
		$.ajax({
			type: "POST",
			url: "/api/subscribe/" + toUserId,
			dataType: "JSON"
		}).done(res => {
			console.log(res);
			$(obj).text("구독취소");
			$(obj).toggleClass("blue");
		}).fail(error => {
			console.log(error);
			alert("구독하기 실패");
		});
	}
}
// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
	$.ajax({
		url: `/api/user/${pageUserId}/subscribe`,
		type: "POST",
		dataType: "json"
	}).done(res => {
		let data = res.data;
		data.forEach(data => {
			let item = getSubscribeModalItem(data)
			$("#subscribeModalList").append(item);
		});

	}).fail(error => {
		alert("error");
	});
	$(".modal-subscribe").css("display", "flex");
}

function getSubscribeModalItem(data) {
	let item = `<div class="subscribe__item" id="subscribeModalItem-1">
					<div class="subscribe__img">
						<img src="/upload/${data.profileImageUrl}" onerror="this.src='/images/person.jpeg'"/>
					</div>
	
					<div class="subscribe__text">
						<h2>${data.username}</h2>
					</div>
					
					<div class="subscribe__btn">`;
	if (!data.equalsUserStatus) { //동일유저가 아닐때 버튼이 만들어 져야함
		if (data.subscribeStatus) {// 구독한 상태
			item += `<button className="cta blue" onClick="toggleSubscribeModal(this)">구독취소</button>`;
		} else {//구독하지 않은 상태
			item += `<button className="cta" onClick="toggleSubscribeModal(this)">구독하</button>`;
		}
	}
	item += `;	
					</div>
        		</div>`;

	return item;
}


// (3) 구독자 정보 모달에서 구독하기, 구독취소
function toggleSubscribeModal(obj) {
	if ($(obj).text() === "구독취소") {
		$(obj).text("구독하기");
		$(obj).toggleClass("blue");
	} else {
		$(obj).text("구독취소");
		$(obj).toggleClass("blue");
	}
}

// (4) 유저 프로파일 사진 변경 (완)
function profileImageUpload() {
	$("#userProfileImageInput").click();

	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}

		// 사진 전송 성공시 이미지 변경
		let reader = new FileReader();
		reader.onload = (e) => {
			$("#userProfileImage").attr("src", e.target.result);
		}
		reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
	});
}


// (5) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
	$(".modal-info").css("display", "none");
}

// (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (8) 구독자 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}






