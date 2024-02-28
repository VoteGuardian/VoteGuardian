
import { candidateListState, voteInfo } from "@/recoil/atoms/voteAtoms"
import { useRecoilState } from "recoil"
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import './InfoContent.scss'
import Button from "../Common/Button/Button";
import Alert from "../Common/Alert/Alert";


export default function InfoContent() {
    const router = useRouter();
    const [vote, setVote] = useRecoilState(voteInfo);
    //투표 기간을 제대로 설정했는지
    const [dateFlag, setDateFlag] = useState(false); 
    //후보자 목록
    const [candidateList, setcandidateList] = useRecoilState(candidateListState);
    //알람창
    const [alert, setAlert] = useState(false);
    const [alertText, setAlertText] = useState('');
    //후보자 한 명
    const [candidate, setCandidate] = useState({});
    //후보자 한 명의 상세 정보를 띄울 때
    const [candidateFlag, setCandidateFlag] = useState(false);

    //투표 시작 날짜를 현재보다 더 전의 날짜로 선택하지 못하게 하기 위함
    const offset = 1000 * 60 * 60 * 9;
    const koreaNow = new Date((new Date()).getTime() + offset);
    const time = koreaNow.toISOString().replace("T", " ").split('.')[0].slice(0,16);

    useEffect(() => {
        if(vote.startAt < vote.finishAt) setDateFlag(true)
    }, [])
    //투표 기간(시작 날짜)
    function handleStartDate(e){
        setVote({
            ...vote,
            startAt: e.target.value
        })
    }
    //투표 기간(마감 날짜)
    function handleEndDate(e) {
        //투표 시작 날짜를 선택하지 않은 상태면 문구를 출력하고 선택한 날짜를 없앰
        if(vote.startAt === '') {
            setAlertText('투표 시작 날짜를 먼저 선택해주세요')
            handleAlert();
            e.target.value = ''
        }
        //투표 시작 날짜가 더 빠를 경우
        else if(vote.startAt >= e.target.value) {
            setAlertText('투표 마감 날짜는 투표 시작 날짜보다 빠를 수 없습니다')
            handleAlert();
            e.target.value = ''
        }
        else {
            setVote({
                ...vote,
                finishAt: e.target.value
            })
            setDateFlag(true);
        }
    }
    //투표 제목
    function handleTitleChange(e) {
        setVote({
            ...vote,
            title: e.target.value
        })
        //제목은 40글자 제한
        if(e.target.value.length > 40) {
            document.getElementsByClassName("title-error")[0].classList.add('show');
            document.getElementsByClassName("title-input")[0].classList.add('error');
        }
        else {
            document.getElementsByClassName("title-error")[0].classList.remove('show');
            document.getElementsByClassName("title-input")[0].classList.remove('error');
        }
    }
    //투표 정보
    function handleContentChange(e) {
        setVote({
            ...vote,
            content: e.target.value
        })
        if(e.target.value.length > 1100) {
            document.getElementsByClassName("content-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("content-error")[0].classList.remove('show');
    }
    //이전 버튼 클릭
    function handlePrev() {
        //투표 종류 선택 페이지로 이동
        router.push('/VoteCreate/Select')
    }
    //다음 버튼을 클릭할 경우
    function handleNext() {
        //투표 기간이 조건에 맞지 않을 경우
        if(!dateFlag) {
            setAlertText('투표 기간이 조건에 맞지 않으니 다시 설정해주세요');
            handleAlert();
        }
        //투표 제목이 조건에 맞지 않을 경우
        else if(vote.title.length == 0 || vote.title.length > 40) {
            setAlertText('투표 제목은 40글자 이내로 작성해주세요');
            handleAlert();
        }
        //투표 정보가 조건에 맞지 않을 경우
        else if(vote.content.length == 0 || vote.content.length > 1100) {
            setAlertText('투표 정보는 1100글자 이내로 작성해주세요');
            handleAlert();
        }
        //투표 후보 수가 조건에 맞지 않을 경우
        else if(candidateList.length == 0) {
            setAlertText('투표 후보는 1개 이상 등록해주세요');
            handleAlert();
        }
        else {
            //후보자 등록 페이지로 이동
            router.push('/VoteCreate/Voter')
        }
    }
    //투표 후보 등록 버튼을 클릭할 경우 후보 등록 페이지로 이동
    function handleCandidateAdd() {
        router.push('/VoteCreate/Candidate')
    }
    //등록된 후보를 클릭할 경우
    function handleCandidateOne(e) {
        //상세 정보 창을 띄우기 위해
        setCandidateFlag(true);
        for(let i = 0; i < candidateList.length; i++) {
            if(candidateList[i].name === e.target.innerText) {
                setCandidate(candidateList[i])
                break;
            }
        }
    }
    //후보 삭제
    function handleCandidateRemove(name) {
        //현재 후보 목록을 받아 해당하는 후보만 삭제할 임시 후보 목록
        let candidateTmp = candidateList.filter((candidate) => candidate.name !== name);
        
        //후보 목록 새로 설정하고 창 닫기
        setcandidateList(candidateTmp);
        setCandidateFlag(false)
    }
    //후보창 닫기
    function handleCandidateClose() {
        setCandidateFlag(false)
    }
    //알람창
    function handleAlert() {
        //나타났다가 2초 뒤 쯤 사라지게 함
        setAlert(true)
        setTimeout(() => {
            setAlert(false)
        }, 2000)
    }

    return (
        <>
            {alert &&
                <Alert type='negative' text={alertText}/>
            }
            {candidateFlag &&
                <div className="candidate-one-wrap">
                    <div className="candidate-one-flex">
                        <div>
                            <div className="candidate-one-name">
                                <p>이름</p>
                                <p>{candidate.name}</p>
                            </div>
                            {candidate.tag && 
                            <div className="candidate-one-tag">
                                <p>태그</p>
                                {candidate.tag.map((tag) => 
                                <div className="tag-group">
                                    <p>#{tag}</p>
                                </div>
                                )}
                            </div>
                            }
                            <div className="candidate-one-content">
                                <p>상세 정보</p>
                                <div>
                                    <p>{candidate.content}</p>
                                </div>
                            </div>
                            {candidate.picture && 
                                <div className="candidate-one-picture">
                                </div>
                            }
                            <div className="button-group-flex">
                                <div onClick={() => handleCandidateRemove(candidate.name)}>
                                    <Button buttonType='select negative' text='삭제'></Button>
                                </div>
                                <div onClick={handleCandidateClose}>
                                    <Button buttonType='select positive' text='닫기'></Button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
            <div className="vote-content-flex">
                <div>
                    <div className="vote-type-wrap">
                    {vote.type === true ?
                        <p>찬반 투표</p>
                        : <p>선거 투표</p>}
                    </div>
                    <div className="vote-date">
                        <p className="info-title">투표 기간</p>
                        <div className="date-flex">
                            <input value={vote.startAt} onInput={handleStartDate} type="datetime-local" min={time} className="time-start"></input>
                            <input value={vote.finishAt} onInput={handleEndDate} type="datetime-local"></input>
                        </div>
                    </div>
                    <div className="vote-title">
                        <p className="info-title">투표 제목</p>
                        <div className="title-input-wrap">
                            <input value={vote.title} className="title-input" onInput={handleTitleChange} type="text" placeholder="투표 제목을 입력하세요"/>
                        </div>
                        <p className="title-error">투표 제목은 40글자 이하여야 합니다</p>
                    </div>
                    <div className="vote-info">
                        <p className="info-title">투표 정보</p>
                        <div className="info-input-wrap">
                            <textarea value={vote.content} onInput={handleContentChange} name="content" cols="50" rows="22" placeholder="투표에 대해서 설명할 내용을 작성하세요"/>
                        </div>
                        <p className="content-error">투표 정보는 1100글자 이하여야 합니다</p>
                    </div>
                    {vote.type === false &&
                    <div className="vote-candidate">
                        <div className="candidate-title-flex">
                            <p className="info-title">투표 후보 등록</p>
                            <p>* 5개 제한</p>
                        </div>
                        
                        <div className="candidate-flex">
                            {candidateList.length < 5 && 
                            <div className="candidate-plus" onClick={handleCandidateAdd}>
                                <p>+</p>
                            </div>
                            }
                            {candidateList && candidateList.map((candidate) => 
                            <div className="candidate-plus" onClick={handleCandidateOne}>
                                <p>{candidate.name}</p>
                            </div>)}
                        </div>
                    </div>}
                    <div className="button-group-flex">
                        <div onClick={handlePrev}>
                            <Button buttonType='select negative' text='이전'></Button>
                        </div>
                        <div onClick={handleNext}>
                            <Button buttonType='select positive' text='다음'></Button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}