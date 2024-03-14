
import './VoterContent.scss'
import Alert from '../../Common/Alert/Alert';
import VoterOne from '../One/VoterOne';
import Button from '@/components/Common/Button/Button';
import { useState } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import { candidateListState, voteInfo, voterListState, voterOneState } from '@/recoil/atoms/voteAtoms';
import { useRouter } from "next/navigation";
import { createVoteOne } from '@/app/api/vote/vote';

export default function VoterContent() {
    const router = useRouter();
    //한명 등록하기, 여러명 등록하기 창 띄우기
    const [voterOne, setVoterOne] = useRecoilState(voterOneState);
    const [voterFileFlag, setVoterFileFlag] = useState(false);
    const vote = useRecoilValue(voteInfo);
    const [alertType, setAlertType] = useState('negative');
    //후보자 목록, 후보자 사진 목록
    const candidateList = useRecoilValue(candidateListState);
    const [voterList, setVoterList] = useRecoilState(voterListState);
    //선택한 투표자 수
    const [voterSelectNum, setVoterSelectNum] = useState(0);
    //알람창
    const [alert, setAlert] = useState(false);
    const [alertText, setAlertText] = useState('');
    
    //투표자 검색
    function handleVoterSearch() {

        console.log('검색')
    }

    //투표자 한 명 등록 버튼 클릭
    function handleVoterOne() {
        setVoterOne(true)
    }
    
    //투표자 여러명 등록
    function handleVoterFileAdd() {
        setVoterFileFlag(true);
    }
    
    //등록된 투표자 전체 선택
    function handleSelectAll(e) {
        const allChecked = e.target.checked;
        const voterCheckList = document.getElementsByName('voter');

        voterCheckList.forEach((check) => {
            check.checked = allChecked;
        })

        if(allChecked === true) setVoterSelectNum(voterList.length);
        else setVoterSelectNum(0)
    }

    //투표자 한 명 선택
    function handleSelectOne(e) {
        const oneChecked = e.target.checked;
        
        if(oneChecked === true) setVoterSelectNum(voterSelectNum+1);
        else setVoterSelectNum(voterSelectNum-1);
    }
    //투표자 한 명 삭제
    function handleRemoveOne(e) {
        //삭제하려고 하는 것과 이메일이 일치할 때 삭제
        const voterEmail = e.target.parentNode.childNodes[2].innerText;

        let voterTmp = voterList.filter((voter) => voter.email !== voterEmail)
        setVoterList(voterTmp);
    }
    //이전 버튼 클릭
    function handlePrev() {
        router.push('/VoteCreate/Information')
    }
    //등록 버튼 클릭
    function handleRegister() {
        //이메일은 더미(회원 이메일)
        const email = "ko123@g.com"
        //투표 등록
        const photoList = [];
        if(voterList.length > 0) {
            const voteReq = {
                email: email,
                title: vote.title,
                content: vote.content,
                type : vote.type,
                startAt: vote.startAt,
                finishAt: vote.finishAt,
                candidateList: candidateList,
                voterList: voterList
            }
            for(let i = 0; i < candidateList.length; i++) {
                if(candidateList[i].picture === true) {
                    const image = localStorage.getItem(candidateList[i].name);
                    photoList.push(image);
                }
            }
            console.log(photoList)
            createVote(voteReq, photoList);
        }
        else {
            setAlertText('투표자는 한 명 이상 등록해야 합니다');
            setAlertType('negative');
            handleAlert();
        }
    }
    //생성 api
    async function createVote(voteReq, photoList) {
        //api 요청
        const result = await createVoteOne(voteReq, photoList);
        if(result === 'OK') {
            setAlertText('투표가 등록되었습니다')
            setAlertType('positive');
            handleAlert();
            setTimeout(() => {
                router.push('/VoteMain')
            }, 1000)
            //세션에 담겨있던 투표 정보 삭제
            sessionStorage.removeItem('persist')
        }
        else console.log('실패')
    }

    //알람창
    function handleAlert() {
        //나타났다가 2초 뒤 쯤 사라지게 함
        setAlert(true)
        setTimeout(() => {
            setAlert(false)
        }, 2000)
    }
    
    return(
        <>
            {alert &&
                    <Alert type={alertType} text={alertText}/>
            }
            {voterOne &&
                <VoterOne/>
            }
            <div className='voter-content-flex'>
                <div className='voter-content-wrap'>
                    <div className='voter-title'>
                        <p>투표자 등록</p>
                    </div>
                    <div className='voter-search-wrap'>
                        <input type='text'></input>
                        <div className='voter-search-button'onClick={handleVoterSearch}>
                            <p>검색</p>
                        </div>
                    </div>
                    <div className='voter-list-big-wrap'>
                        <div className='voter-list-top'>
                            <div className='voter-list-button-group'>
                                <div onClick={handleVoterOne}>
                                    <p>한 명 등록하기</p>
                                </div>
                                <div onClick={handleVoterFileAdd}>
                                    <p>파일로 등록하기</p>
                                </div>
                            </div>
                            <div className='voter-num-group'>
                                <p>전체 {voterList.length}</p>
                                <p>선택 {voterSelectNum}</p>
                            </div>
                        </div>
                        <div className='voter-list-content'>
                            <table>
                                <thead>
                                    <th><input type='checkbox' onClick={handleSelectAll} value="selectAll"></input></th>
                                    <th>이름</th>
                                    <th>이메일</th>
                                    <th>삭제</th>
                                </thead>
                                <tbody>
                                    {voterList.length > 0 ? voterList.map((voter) =>
                                    <tr>
                                        <td><input type="checkbox" name="voter" onClick={handleSelectOne}/></td>
                                        <td>{voter.name}</td>
                                        <td>{voter.email}</td>
                                        <td onClick={handleRemoveOne}>X</td>
                                    </tr>
                                    )
                                    :
                                    <div>
                                        등록된 투표자가 없습니다
                                    </div>
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div className="button-group-flex">
                        <div onClick={handlePrev}>
                            <Button buttonType='select negative' text='이전'></Button>
                        </div>
                        <div onClick={handleRegister}>
                            <Button buttonType='select positive' text='등록'></Button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}