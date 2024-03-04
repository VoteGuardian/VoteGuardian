'use server'

const VOTE_URL = "http://localhost:8080/vote";

export async function getCreateVoteList(state, page, email) {
    const response = await fetch(`${VOTE_URL}/main?state=${state}&page=${page}&email=${email}`, {
        method: 'GET',
        //해당 쿼리에 대해서는 자동으로 캐싱 작업을 하지 않고 , 매번 새로운 요청이 있을 때마다 새롭게 데이터를 불러오도록 설정해 주는 것
        cache: "no-store"
    })
    try {
        const result = await response.json();
        if(result.status == 'OK') {
            console.log("성공")
            return result.responseDto;
        }
    } 
    catch(e) {
        console.log("에러")
        console.log(e);
    }
}    

export async function getParticipateVoteList(state, page, email) {
    const response = await fetch(`${VOTE_URL}/link?state=${state}&page=${page}&email=${email}`, {
        method: 'GET',
        cache: "no-store"
    })
    try {
        const result = await response.json();
        if(result.status == 'OK') {
            console.log("성공")
            return result.responseDto;
        }
    } 
    catch(e) {
        console.log("에러")
        console.log(e);
    }
}

export async function createVoteOne(vote) {
    const formData = new FormData();
    formData.append('request', new Blob([JSON.stringify(vote)], { type: 'application/json' }))
    const response = await fetch(VOTE_URL, {
        method: 'POST',
        body: formData
    })
    try {
        const result = await response.json();
        if(result.status == 'OK') {
            console.log('성공')
            return 'OK'
        }
    }
    catch(e) {
        console.log(e)
    }
}